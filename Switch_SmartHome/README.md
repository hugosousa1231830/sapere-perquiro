# SmartHome Project Overview

## Introduction
The SmartHome project represents a collaborative effort by a team of 10 participants enrolled in the Switch - Software Engineering course. Designed as a practical sandbox, the project allows the team to apply newly acquired knowledge in a progressive and iterative manner on a weekly basis. Although I do not claim exclusive ownership of the project, my contributions have been substantial, encompassing decision-making, initial commits, refactoring, and overall project supervision.

## Backend Focus
The backend architecture of the SmartHome project is founded on Domain Driven Development (DDD) principles and utilizes the concentric (ONION) architecture. Key characteristics of the backend include:

- **Domain Driven Development (DDD)**: Ensures the code closely aligns with the business domain.
- **ONION Architecture**: Promotes a clean separation of concerns and enhances maintainability.
- **RESTful Controllers**: Implemented using HATEOAS (Hypermedia as the Engine of Application State) to improve API interactions and client navigation.
- **Skill Evolution**: The project intentionally retains some early-stage features to showcase the progression and development of our skills over time.

## Documentation
The project includes comprehensive documentation located in the `docs` folder, which encompasses extensive design and analysis artifacts. This documentation includes the C4+1 model, providing a clear and structured overview of the system. Additionally, the `archive` subfolder within `docs` offers a detailed record of the project's progression across each sprint, ensuring transparency and traceability of development activities.

## Frontend Exploration
Our exploration into the frontend was brief and primarily experimental. The frontend code, situated in the `frontend/smarthome` directory, is not optimized but serves to demonstrate basic functionality. Key features include:

- **JavaScript/React Implementations**: Basic implementations capable of rendering pages and performing a few API requests.
- **Limited Utilization of HATEOAS**: The frontend does not fully leverage the backend's HATEOAS capabilities.
- **Suboptimal Design**: The frontend code does not adhere to best practices in frontend design and optimization, reflecting its experimental nature.

## Running the Frontend
To explore the frontend, follow these steps:

1. Navigate to `frontend/smarthome`.
2. Open `.env.demo` and set the `REACT_APP_BACKEND_API_URL` variable to `http://localhost:8080`.
3. Ensure Node.js is installed on your system.
4. In a terminal, navigate to `frontend/smarthome`.
5. Run `npm start`.

Your application should launch in your browser, ready for exploration and customization.
