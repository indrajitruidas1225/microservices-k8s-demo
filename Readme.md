# Spring Boot Microservices on Kubernetes 🚀

This project demonstrates a **production-style microservices architecture** built using **Spring Boot**, deployed on **Kubernetes (k3s)**, and designed to simulate a simplified **e-commerce system**.

The goal of this project is to practice and demonstrate important backend and cloud concepts such as:

* Microservices architecture
* Service-to-service communication
* Distributed transactions (Saga Pattern)
* Containerization with Docker
* Kubernetes deployments and services

---

# 🧱 Architecture Overview

The system consists of four independent microservices:

| Service             | Responsibility                               |
| ------------------- | -------------------------------------------- |
| **User Service**    | Manages users                                |
| **Cart Service**    | Manages items in a user's cart               |
| **Order Service**   | Creates orders and orchestrates the workflow |
| **Payment Service** | Processes payments for orders                |

Each service is **independently deployable** and owns its **own database** (a core microservices principle).

---

# 🔁 Microservice Flow

When an order is placed:

1. **Order Service** receives the request
2. It verifies the user via **User Service**
3. Fetches items from **Cart Service**
4. Calls **Payment Service**
5. Updates order status depending on payment result

Flow:

```
Client
   ↓
Order Service
   ↓
User Service
   ↓
Cart Service
   ↓
Payment Service
```

---

# ⚙️ Technologies Used

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* OpenFeign (service communication)
* Lombok

### Architecture Patterns

* Microservices architecture
* Saga Pattern (for distributed transactions)
* REST APIs

### DevOps

* Docker
* Kubernetes (k3s)
* Containerd runtime

---

# 📦 Project Structure

```
microservices-k8s-demo
│
├── user-service
├── cart-service
├── order-service
├── payment-service
│
└── k8s
    ├── user-deployment.yml
    ├── cart-deployment.yml
    ├── order-deployment.yml
    ├── payment-deployment.yml
    ├── user-service.yml
    ├── cart-service.yml
    ├── order-service.yml
    └── payment-service.yml
```

Each microservice follows a layered architecture:

```
controller
service
repository
entity
dto
client (Feign clients)
common
```

---

# 🐳 Docker Setup

Each microservice is containerized using a Dockerfile.

Example build command:

```
docker build -t user-service:1.0 .
```

Images are then loaded into the Kubernetes cluster.

---

# ☸️ Kubernetes Deployment

Services are deployed as:

* **Deployments** (for pods)
* **ClusterIP Services** (internal communication)
* **NodePort Service** (external access)

Check running pods:

```
kubectl get pods
```

Example output:

```
user-service
cart-service
order-service
payment-service
```

---

# 🌐 Service Communication

Inside Kubernetes, services communicate using **Kubernetes DNS names**:

```
http://user-service:8081
http://cart-service:8082
http://payment-service:8084
```

This removes the need for hardcoded hostnames.

---

# 📡 Exposing the Application

`order-service` is exposed using **NodePort** so it can be accessed externally.

Example:

```
http://<NODE-IP>:30083/orders
```

Example request:

```
POST /orders
{
  "userId": 1
}
```

---

# 🔄 Distributed Transaction Handling

This project demonstrates a simplified **Saga Pattern**.

Order states:

```
PENDING
CONFIRMED
CANCELLED
```

Flow:

```
Create Order → PENDING
      ↓
Call Payment Service
      ↓
Payment Success → CONFIRMED
Payment Failed  → CANCELLED
```

This avoids traditional distributed transaction issues.

---

# 📊 Key Microservices Principles Demonstrated

* Each service owns its **own database**
* Services communicate via **REST APIs**
* Services are **independently deployable**
* Failures handled using **Saga Pattern**
* Infrastructure managed using **Kubernetes**

---

# 🚀 Running the Project

1️⃣ Build services

```
mvn clean package
```

2️⃣ Build Docker images

```
docker build -t user-service:1.0 .
```

3️⃣ Deploy to Kubernetes

```
kubectl apply -f k8s/
```

4️⃣ Verify pods

```
kubectl get pods
```

5️⃣ Test API

```
POST http://<NODE-IP>:30083/orders
```

---

# 🔮 Future Improvements

Possible improvements to make this closer to production:

* API Gateway (Spring Cloud Gateway)
* Service Discovery (Eureka / Kubernetes-native)
* Ingress Controller
* Centralized Configuration
* Observability (Prometheus + Grafana)
* Event-driven Saga using Kafka
* Horizontal Pod Autoscaling

---

# 📚 What This Project Demonstrates

This project demonstrates how to:

* Design microservices using Spring Boot
* Implement service communication
* Handle distributed transactions
* Containerize applications
* Deploy microservices to Kubernetes

It serves as a **hands-on learning project for backend engineers exploring cloud-native architectures**.

---

# 👨‍💻 Author

Built as a hands-on project to explore **Spring Boot microservices and Kubernetes deployment workflows**.
