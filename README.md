\# Spring Boot Application - Docker \& AWS Deployment



\## 📋 Table of Contents

\- \[Overview](#overview)

\- \[Architecture](#architecture)

\- \[Prerequisites](#prerequisites)

\- \[Local Development Setup](#local-development-setup)

\- \[Docker Deployment](#docker-deployment)

\- \[Docker Hub](#docker-hub)

\- \[AWS EC2 Deployment](#aws-ec2-deployment)

\- \[Testing](#testing)

\- \[Screenshots](#screenshots)



---



\## 🎯 Overview



This project demonstrates a complete CI/CD workflow for deploying a Spring Boot REST API application using Docker containers and AWS EC2. The application provides a simple greeting service that can be accessed via HTTP endpoints.



\*\*Key Features:\*\*

\- RESTful API built with Spring Boot

\- Containerized using Docker

\- Multi-container orchestration with Docker Compose

\- Cloud deployment on AWS EC2

\- Scalable architecture supporting multiple instances



---



\## 🏗️ Architecture



\### Application Architecture



```

┌─────────────────────────────────────────┐

│          Client (Browser/API)           │

└────────────────┬────────────────────────┘

&nbsp;                │ HTTP Request

&nbsp;                ▼

┌─────────────────────────────────────────┐

│         AWS EC2 Instance                │

│  ┌───────────────────────────────────┐  │

│  │     Docker Container              │  │

│  │  ┌─────────────────────────────┐  │  │

│  │  │   Spring Boot Application   │  │  │

│  │  │   - HelloRestController     │  │  │

│  │  │   - Port: 33025             │  │  │

│  │  └─────────────────────────────┘  │  │

│  └───────────────────────────────────┘  │

│         Port Mapping: 42000:33025       │

└─────────────────────────────────────────┘

```



\### Technology Stack



\- \*\*Backend\*\*: Spring Boot 3.3.3

\- \*\*Language\*\*: Java 17

\- \*\*Build Tool\*\*: Maven

\- \*\*Containerization\*\*: Docker

\- \*\*Orchestration\*\*: Docker Compose

\- \*\*Cloud Platform\*\*: AWS EC2

\- \*\*Container Registry\*\*: Docker Hub



---



\## ✅ Prerequisites



Before starting, ensure you have the following installed:



\- \*\*Java JDK 17+\*\*: \[Download here](https://adoptium.net/)

\- \*\*Maven 3.6+\*\*: \[Download here](https://maven.apache.org/download.cgi)

\- \*\*Docker Desktop\*\*: \[Download here](https://www.docker.com/products/docker-desktop/)

\- \*\*Git\*\*: \[Download here](https://git-scm.com/)

\- \*\*AWS Account\*\*: \[Sign up here](https://aws.amazon.com/)



\### Verify Installations



```bash

java -version

mvn -version

docker --version

git --version

```



---



\## 🚀 Local Development Setup



\### Step 1: Clone the Repository



```bash

git clone https://github.com/Haider1204/spring-docker-aws-deployment

.git

cd spring-docker-aws-deployment



```



\### Step 2: Build the Project



```bash

mvn clean install

```



Expected output:

```

\[INFO] BUILD SUCCESS

\[INFO] Total time: XX.XXX s

```



\### Step 3: Run Locally (Without Docker)



```bash

java -cp "target/classes;target/dependency/\*" co.edu.escuelaing.dockerprimer.RestServiceApplication

```



\*\*Note\*\*: On Linux/Mac use `:` instead of `;` in the classpath.



\### Step 4: Test the Application



Open your browser and navigate to:

```

http://localhost:33025/greeting

http://localhost:33025/greeting?name=Haider

```



Expected responses:

```

Hello, World!

Hello, Haider!

```



---



\## 🐳 Docker Deployment



\### Build Docker Image



```bash

docker build --tag dockersparkprimer .

```



\### Run Single Container



```bash

docker run -d -p 34000:33025 --name myapp dockersparkprimer

```



\### Run Multiple Containers



```bash

docker run -d -p 34000:33025 --name container1 dockersparkprimer

docker run -d -p 34001:33025 --name container2 dockersparkprimer

docker run -d -p 34002:33025 --name container3 dockersparkprimer

```



\### Verify Running Containers



```bash

docker ps

```



\### Test Multiple Instances



```

http://localhost:34000/greeting?name=Instance1

http://localhost:34001/greeting?name=Instance2

http://localhost:34002/greeting?name=Instance3

```



---



\## 🎼 Docker Compose



Docker Compose allows you to run multi-container applications with a single command.



\### Start Services



```bash

docker-compose up -d

```



This creates:

\- \*\*web\*\*: Spring Boot application container

\- \*\*db\*\*: MongoDB database container



\### Stop Services



```bash

docker-compose down

```



\### View Logs



```bash

docker-compose logs -f web

```



\### Test Docker Compose Deployment



```

http://localhost:8087/greeting?name=DockerCompose

```



---



\## 🐋 Docker Hub



\### Login to Docker Hub



```bash

docker login

```



\### Tag the Image



```bash

docker tag dockersparkprimer YOUR\_DOCKERHUB\_USERNAME/firstsprkwebapprepo:latest

```



\### Push to Docker Hub



```bash

docker push YOUR\_DOCKERHUB\_USERNAME/firstsprkwebapprepo:latest

```



\### Pull from Docker Hub (on any machine)



```bash

docker pull YOUR\_DOCKERHUB\_USERNAME/firstsprkwebapprepo:latest

```



---



\## ☁️ AWS EC2 Deployment



\### Step 1: Launch EC2 Instance



1\. Go to \*\*AWS Console\*\* → \*\*EC2\*\*

2\. Click \*\*"Launch Instance"\*\*

3\. Configure:

&nbsp;  - \*\*AMI\*\*: Amazon Linux 2

&nbsp;  - \*\*Instance Type\*\*: t2.micro (Free tier eligible)

&nbsp;  - \*\*Key Pair\*\*: Create or select existing

&nbsp;  - \*\*Security Group\*\*: Allow SSH (port 22)



\### Step 2: Connect to EC2 Instance



```bash

ssh -i your-key.pem ec2-user@YOUR\_EC2\_PUBLIC\_IP

```



\### Step 3: Install Docker on EC2



```bash

sudo yum update -y

sudo yum install docker -y

sudo service docker start

sudo usermod -a -G docker ec2-user

```



\*\*Logout and login again\*\* for group changes to take effect.



\### Step 4: Pull and Run Docker Image



```bash

docker pull YOUR\_DOCKERHUB\_USERNAME/firstsprkwebapprepo:latest

docker run -d -p 42000:33025 --name myapp YOUR\_DOCKERHUB\_USERNAME/firstsprkwebapprepo:latest

```



\### Step 5: Configure Security Group



1\. Go to \*\*EC2 Dashboard\*\* → \*\*Security Groups\*\*

2\. Select your instance's security group

3\. Click \*\*"Edit inbound rules"\*\*

4\. Add rule:

&nbsp;  - \*\*Type\*\*: Custom TCP

&nbsp;  - \*\*Port\*\*: 42000

&nbsp;  - \*\*Source\*\*: 0.0.0.0/0

5\. Save rules



\### Step 6: Test Deployment



```

http://YOUR\_EC2\_PUBLIC\_DNS:42000/greeting

http://YOUR\_EC2\_PUBLIC\_DNS:42000/greeting?name=AWS

```



Example:

```

http://ec2-54-197-216-133.compute-1.amazonaws.com:42000/greeting?name=CloudDeployment

```



Expected response:

```

Hello, CloudDeployment!

```



---



\## 🧪 Testing



\### API Endpoints



| Method | Endpoint | Parameters | Response |

|--------|----------|------------|----------|

| GET | `/greeting` | None | `Hello, World!` |

| GET | `/greeting?name={name}` | name (string) | `Hello, {name}!` |



\### Testing Commands



\*\*Using curl:\*\*

```bash

curl http://localhost:33025/greeting

curl http://localhost:33025/greeting?name=TestUser

```



\*\*Using PowerShell:\*\*

```powershell

Invoke-WebRequest -Uri "http://localhost:33025/greeting"

```



---



\## 📸 Screenshots



\### Local Development

!\[Local Development](screenshots/local-run.png)



\### Docker Container Running

!\[Docker Containers](screenshots/docker-ps.png)



\### Multiple Container Instances

!\[Multiple Instances](screenshots/multiple-containers.png)



\### Docker Hub Repository

!\[Docker Hub](screenshots/dockerhub.png)



\### AWS EC2 Instance

!\[EC2 Instance](screenshots/ec2-instance.png)



\### Application Running on AWS

!\[AWS Deployment](screenshots/aws-running.png)



---



\## 📊 Project Structure



```

dockerprimer/

├── src/

│   └── main/

│       └── java/

│           └── co/edu/escuelaing/dockerprimer/

│               ├── HelloRestController.java      # REST API Controller

│               └── RestServiceApplication.java   # Main Spring Boot Application

├── Dockerfile                                     # Docker image instructions

├── docker-compose.yml                            # Multi-container configuration

├── pom.xml                                       # Maven configuration

├── .gitignore                                    # Git ignore rules

└── README.md                                     # This file

```



---



\## 🔧 Configuration Details



\### Port Configuration



\- \*\*Application Internal Port\*\*: 33025

\- \*\*Docker Compose Port\*\*: 8087

\- \*\*AWS EC2 Port\*\*: 42000

\- \*\*Local Testing Ports\*\*: 34000, 34001, 34002



\### Environment Variables



The application reads the PORT environment variable:

```java

private static int getPort() {

&nbsp;   if (System.getenv("PORT") != null) {

&nbsp;       return Integer.parseInt(System.getenv("PORT"));

&nbsp;   }

&nbsp;   return 33025;

}

```



---



\## 🐛 Troubleshooting



\### Issue: Docker daemon not running

\*\*Solution\*\*: Start Docker Desktop and ensure it's running.



\### Issue: Port already in use

\*\*Solution\*\*: 

```bash

docker stop $(docker ps -q)

```



\### Issue: Cannot connect to EC2 instance

\*\*Solution\*\*: Verify Security Group has port 42000 open for inbound traffic.



\### Issue: Maven build fails

\*\*Solution\*\*: Ensure Java 17+ is installed and JAVA\_HOME is set correctly.



---



\## 📚 Additional Resources



\- \[Spring Boot Documentation](https://spring.io/projects/spring-boot)

\- \[Docker Documentation](https://docs.docker.com/)

\- \[AWS EC2 Documentation](https://docs.aws.amazon.com/ec2/)

\- \[Maven Documentation](https://maven.apache.org/guides/)



---



\## 👨‍💻 Author



\*\*Haider Estiven Rodriguez Pinto\*\*

\- GitHub: \[@haider1204](https://github.com/haider1204)





