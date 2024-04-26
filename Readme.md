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

## Data
Annual Working Hours: https://www.kaggle.com/datasets/saurabhbadole/annual-working-hours-dataset-1870-1970