# Super simple Play application

The most basic Play application I could think of.

### Run locally

Start service

```sbt run```

The service will then run locally at localhost:9000

Calling the service requires a "text" field in the body. For example:

```
curl -X POST \
  http://localhost:9000 \
  -H 'Content-Type: application/json' \
  -d '{"text": "input-value"}'
  ```
