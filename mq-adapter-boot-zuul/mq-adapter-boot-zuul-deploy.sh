#!/bin/bash
kubectl apply -f docker-pv-namespace.yaml

kubectl apply -f persistent-volume.yaml

kubectl apply -f persistent-volume-claim.yaml

kubectl delete -f mq-adapter-boot-zuul.yaml

kubectl apply -f repository-secret.yaml

kubectl apply -f mq-adapter-boot-zuul.yaml

kubectl apply -f www_mq_ingress.yaml