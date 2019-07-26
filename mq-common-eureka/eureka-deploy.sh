#!/bin/bash

kubectl apply -f persistent-volume.yaml

kubectl apply -f persistent-volume-claim.yaml

kubectl delete -f mq-common-eureka.yaml

kubectl apply -f repository-secret.yaml

kubectl apply -f mq-common-eureka.yaml

kubectl apply -f www_mq_ingress.yaml