# Simple Play application handling multiple data inputs

The most basic Play application which is able to handle multiple different data inputs in the request body.

### Run locally

Start service

```sbt run```

The service will then run locally at localhost:9000

Calling the service using `curl`:

```
curl -X POST http://localhost:9000 \
    -H 'Content-Type: application/json' \
    -d '{"text": "input-value"}'
```
