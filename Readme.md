# AAI Hackathon: graphql-java

## Run the project

1. Build docker image
```shell
docker build -t hackathon-graphql-java:latest .
```

2. Run container
```shell
docker run -p 9555:9555 hackathon-graphql-java:latest
```
*You can change the port if you want to.*

## Usage

### GraphQL

| Endpoint | Path       | Method |
| -------- |------------| ------ |
| GraphQL | `/graphql` | `POST` |
| Playground | `/` | `GET` |

*Introspection is enabled*

### REST
Examples:
 - GET `/rest/annual-working-hours`
 - GET `/rest/annual-working-hours?filterBy=name&filterValue=Belgium`
 - GET `/rest/annual-working-hours?filterBy=abbrev&filterValue=BEL`
 - GET `/rest/annual-working-hours?filterBy=year&filterValue=1870`

### Stored Requests Endpoint
Examples:
 - GET `/debug/requests`
 - GET `/debug/requests?tag=my-tag`

Delete all stored requests:
 - DELETE `/debug/requests`

## About requests

Requests that are send to the `/graphql` endpoint are being stored in the `/debug/requests` endpoint.

You can add tags to the request by having an header called `X-DEBUG-TAG` when being send to the `/graphql` endpoint.

Example:
```
POST /graphql
X-DEBUG-TAG: my-tag

{
  "query": "{ annualWorkingHours { countryName countryAbbrev year hours } }"
}
```

By querying the endpoint `/debug/requests?tag=my-tag` you can receive something like this:
```
[
    {
        "id": "74f24734-d3d2-445b-8e75-f2cf9e262af9",
        "tag": "my-tag",
        "date": "29-04-2024 10:49:14",
        "headers": {
            "Accept": "application/json",
            "User-Agent": "Go-http-client/1.1",
            "X-Forwarded-Host": "gql-java.hackathon.test",
            "X-Forwarded-Proto": "http",
            "Host": "gql-java.hackathon.test",
            "Accept-Encoding": "gzip",
            "X-Debug-Tag": "my-tag",
            "Content-Type": "application/json"
        },
        "body": "{ \"query\": \"{ annualWorkingHours { countryName countryAbbrev year hours } }\" }"
    }
]
```

## Data
Annual Working Hours: https://www.kaggle.com/datasets/saurabhbadole/annual-working-hours-dataset-1870-1970