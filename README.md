![Logo](https://i.postimg.cc/Prh7tGGn/simple-banking-app-logo.jpg)

# Blog App Application

A Blog Web Application that simulates the day-to-day operations of a post.

## Authors

- [@olasoj](https://www.github.com/olasoj)

## 🚀 About Me

Olasoji Ige works at Paga as a software engineer. He holds a Bachelor of Science in Computer Science from Babcock University and has worked in enterprise software development for over three years. He began his career in investment and business analytics before transitioning to payments. His background in large-scale system design and implementation has given him a mature and realistic perspective on enterprise software solutions.
## Features

**The following functionalities are available in this application:**

- Users can read post post list without authentication.

- Users can sign up to create post post.

- Users can log in to create a post post.

- Users can log in to edit a post post if they are the authors of the post.

- Users can log in to delete a post post if they are the authors of the post.

- Users can create an account and receive a unique account id after signing up. When registering, the user must provide an account name that does not already exist on the platform.

- The username and password are used to authenticate the user, which results in the user receiving an access token if successful.

- Except for reading post post, login and registration, every request must have a valid access token.

- This system was built using the spring-boot framework with spring security, without a database, and following the best principles in software engineering.

## Environment Variables

This project made no use of environmental variables.

## Run Locally

Clone the project

```bash
   git clone -b main https://github.com/olasoj/simple-post-app.git
```

Go to the project directory

```bash
  cd simple-post-app
```

Start the simple-post-app

```bash
    ./gradlew bootRun
```

## Documentation

[Documentation](https://post-post-api-doc.surge.sh/)
- Check the resources/docs folder for other documentation

## Tech Stack

**Client:** Postman

**Server:** Java, Spring boot
