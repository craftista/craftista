
# Craftista - A Perfect App for Devops Projects

We, at [School of Devops](www.schoolofdevops.com) take pride to introduce you to Craftista, a Polyglot, Micro-Services based application, built with the Modern Tech stack, specially designed to be used as a learning app to buid Devops Projects with.  


## What is Craftista: Celebrating the Art of Origami 

Welcome to Craftista, a unique web platform dedicated to the beautiful and intricate world of origami. Craftista is a place where origami enthusiasts and artists come together to showcase their creations, share their passion, and engage with a like-minded community. Our platform allows users to explore a diverse range of origami art, vote for their favorites, and get inspired by the daily featured origami.

### Features

**Origami Showcase**: Discover a wide array of origami creations, ranging from traditional designs to contemporary art pieces. Each origami has its own story and charm, waiting to be unfolded.

**User Voting System**: Participate in the community by voting for your favorite origami pieces. See what creations are trending and show your support for the artists.
Daily Origami Recommendation: Be greeted daily with a new origami masterpiece, handpicked to inspire and ignite your passion for paper folding.

**Origami of the Day**: Learn more about origami artists, their work, and their journey into the world of paper art.

**Interactive Community**: Engage with other origami enthusiasts, share insights, and discuss techniques.


## The Architecture - Behind the Scenes

Craftista is not just an origami platform; it's a demonstration of modern web application development and microservices architecture. It leverages multiple backend services, including:

![Craftista Architevture](docs/Craftista-Architecture-SchoolofDevops-CC-BY-NC-SA4.0.jpg "Craftista Architecture")

**Catalogue Service**: Manages the origami showcase, including origami details and images.  
**Voting Service**: Handles the voting functionality, allowing users to vote for their favorite origami.  
**Daily Origami Service**: Selects and presents the daily origami recommendation.  

Each service is built using a different technology stack, showcasing polyglot persistence and diverse backend technologies.

## For Developers and DevOps Enthusiasts
Craftista serves as a perfect sandbox for developers and DevOps practitioners. The microservices architecture of the application makes it an ideal candidate for experimenting with containerization, orchestration, CI/CD pipelines, and cloud-native technologies. It's designed to be a hands-on project for learning and implementing DevOps best practices.

What makes this a perfect Sample Application for Devops Projects ?

**Real Life Like - Micro Services, Polyglot App:** If you look at the architecture and the services of Craftista App, it resembles a real life use case. It's a polyglot microservices based application, with multiple backend services, each with its own technology stack. You can think of it as a simplified version of a E-Commerce platform as it has the essential services such as a Modern UI written in Node.Js, a Product Catalogue Service, a Recommendation Engine and even a User Review App (Voting Service). When you are working with Craftista, it is as good as building a Real Life Project. 


**Modern Tech Stack:** 
We have made deliberate efforts to choose the technologies commonly used by organisations across the globe to build modern applications which technology choice such as  Express.js Framework based off Node.js, Golang, Python Flask Framework and Java Based Spring Boot Framework. 

**Simple Design:**
We have deliberately kept the design simple by removing a lot of additional services such as Shopping Carts, User Management, Payments Processing, Order Management to keep the scope of the project manageable. You will not get lost into the complexity of the architecture and get overwhelmed by the magnitute of services to work with. Yet it is still sophisticated enough for you to get a taste of a Real World Project. 

**Iterative, Expandable and Resilient:**
One of the reasons why this is a perfect learning app is at no point in time you would feel that its not good enough. Unlike a Real World app, which needs many different servies, database backends to be configured in order to have the MVP working,  You deploy one service with Craftista, you have a working UI Framework, without any backend services or databases being involved. And when you deploy additional services iteratively, the page keeps on getting richer, making it a perfect sample app to work with. 

**Prints System Info:**
While building Devops Projects, you are going to deploy this app in containers, take it to kubernetes, scale it and put it behind load balancers. When that happens, how do you get the feedback on if its running within a container or not? Wouldn't it be nicer to find out if its running within a Kubernetes Cluster or not ? How do you validate the Load Balancer is working (should show differnt hostnames/ips every time you refresh). Thats where we have added the Sytems Info section which shows you all this relevant data. 
![System Info](docs/sysinfo.png "System Info")

**Ability to Show the Version:**

**Backend Service Status Dashboard:**

**Incorporates Unit Tests  & Integration Tests:**

**Every Service with UI (including APIs):**

**Mono Repo Structure:**



## Contributing
We welcome contributions from the community! Whether you're an origami artist wanting to showcase your work, a developer interested in microservices, or just someone enthusiastic about the project, your contributions are valuable. Check out our contributing guidelines for more information.

## License
Craftista is open-sourced under the Apache License 2.0.





