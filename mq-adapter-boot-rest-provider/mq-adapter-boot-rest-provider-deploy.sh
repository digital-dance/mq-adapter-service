#!/bin/bash
kubectl apply -f docker-pv-namespace.yaml

kubectl apply -f persistent-volume.yaml

kubectl apply -f persistent-volume-claim.yaml

kubectl delete -f mq-adapter-boot-rest-provider.yaml

kubectl apply -f repository-secret.yaml

#docker rmi -f www.digital.dance.com:5001/repository/digital.dance.docker/mq-adapter-boot-rest-provider:v1

kubectl apply -f mq-adapter-boot-rest-provider.yaml

#kubectl delete -f www_mq_ingress.yaml

kubectl apply -f www_mq_ingress.yaml