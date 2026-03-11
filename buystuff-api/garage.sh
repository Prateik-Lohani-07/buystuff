#!/bin/bash

docker run \
  -d \
  --name garaged \
  -p 3900:3900 -p 3901:3901 -p 3902:3902 -p 3903:3903 \
  -v "$(pwd)"/garage/garage.toml:/etc/garage.toml \
  -v "$(pwd)"/garage/meta:/var/lib/garage/meta \
  -v "$(pwd)"/garage/data:/var/lib/garage/data \
  dxflrs/garage:v2.2.0