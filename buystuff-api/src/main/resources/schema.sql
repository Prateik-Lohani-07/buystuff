CREATE OR REPLACE FUNCTION update_revenue()
RETURNS TRIGGER AS $$
BEGIN
	-- only update it status transitions to 'DELIVERED'
	IF OLD.status <> 'DELIVERED' AND NEW.status = 'DELIVERED' 
	THEN
		INSERT INTO sales_info(product_id, quantity_sold, revenue, created_at, updated_at)
		SELECT
			product_id,
			quantity,
			price * quantity,
			NOW(),
			NOW()
		FROM order_items
		WHERE order_id = NEW.order_id
		ON CONFLICT (product_id)	
		DO UPDATE SET 
			revenue = sales_info.revenue + EXCLUDED.revenue,
			quantity_sold = sales_info.quantity_sold + EXCLUDED.quantity_sold,
			updated_at = NOW()
		;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;;

CREATE OR REPLACE TRIGGER order_delievered
AFTER UPDATE ON orders FOR EACH ROW
EXECUTE FUNCTION update_revenue();;

