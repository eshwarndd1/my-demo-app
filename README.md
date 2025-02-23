# demo-app
This CI/CD pipeline automates the following:
1. Start minikube : minikube start command
2. **Build Maven** -> **Build Docker Image** -> **Push Image to Docker Hub** -> **Deploy to Kubernetes**, this are the stages for CI/CD pipeline, I have used Jenkins to automate the process.
3. After CI/CD builds and complete all the stages : perform this command 
kubectl get pods : to check it demo-api-79677997f6-cqz9s and postgres is running
kubectl get services : to check the status of thier services
minikube service demo-api-service --url : to get the url where is the application is running
eg : 
http://127.0.0.1:59996 this was given as base url, 

# To create customer
curl --location 'http://127.0.0.1:59996/customers' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "John",
    "middleName": "Doe",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "phoneNumber": "1234567890"
}'

# To get customer
http://127.0.0.1:60469/customers

# To delete customer
curl --location --request DELETE 'http://127.0.0.1:60469/customers/ba554fad-daab-43b1-8a34-5960ca8e161f'
# To update customer
curl --location --request PUT 'http://127.0.0.1:60469/customers/79fe36fe-23c1-4f21-ac93-c075a9311aea' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Aisha",
    "middleName": "abc",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "9876543210"
}'


The screenshots of the test are attached in this repo.

# To get customer on the basis of UUID
curl --location 'http://127.0.0.1:60469/customers/72fe36fe-23c1-4f21-ac93-c075a9311aea' \
--header 'Content-Type: application/json'


