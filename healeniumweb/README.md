# Running Healinium with Docker

This guide will walk you through the steps to download and start Docker, and then execute Healinium within a Docker container. Also, this guide will walk you through the steps to add Healinium as a dependency in your Maven project, initialize the self-healing driver, and configure the `healinium.properties` file.

## Prerequisites

Before you begin, ensure you have the following prerequisites installed on your system:

- [Docker](https://www.docker.com/get-started) - Containerization platform used to run Healinium in a container.

## Step 1: Download and Install Docker

1. Visit the [Docker website](https://www.docker.com/get-started) and follow the instructions to download and install Docker for your operating system.

## Step 2: Verify Docker Installation

1. Once Docker is installed, open a terminal or command prompt.
2. Run the following command to verify that Docker is installed correctly:

   ```bash
   docker --version

   # Using Healinium with Maven

## Step 3: Add Healinium Dependency to Your Maven Project

1. Open your project's `pom.xml` file.
2. Add the following dependency to the `<dependencies>` section:
   <dependency>
    <groupId>com.epam.healenium</groupId>
    <artifactId>healenium-web</artifactId>
    <version>3.5.0</version>
    </dependency>


Note: get latest dependency from https://mvnrepository.com/artifact/com.epam.healenium/healenium-web

## Step 4: Initialize the Self-Healing Driver in Your Test Code

In your test code, initialize the self-healing driver before performing any actions on the web application.
Here's an example of how you can initialize the self-healing driver:

      WebDriver driver;
      WebDriverManager.chromedriver().setup();
      WebDriver chromedriver = new ChromeDriver();
      // declare delegate
      // create Self-healing driver
      driver = SelfHealingDriver.create(chromedriver);
      driver.manage().window().maximize();

## Step 5: Configure the healinium.properties File

Create a file named healinium.properties in the root directory of your project.
Add the following properties to the file:
    recovery-tries = 1
    score-cap = 0.5
    heal-enabled = true
    hlm.server.url = http://localhost:7878
    hlm.imitator.url = http://localhost:8000

recovery-tries - list of proposed healed locators

heal-enabled - flag to enable or disable healing. Also you can set this value via -D or System properties, for example to turn off healing for current test run: -Dheal-enabled=false

score-cap - score value to enable healing with predefined probability of match (0.5 means that healing will be performed for new healed locators where probability of match with target one is >=50% )

hlm.server.url - ip:port or name where hlm-backend instance is installed

hlm.imitator.url - ip:port or name where imitate instance is installed

Refer : folder healeniumweb/src/test/resources/healenium.properties

## Step 6: Create init.sql inside folder structure 

project-root/
└── infra/
    └── db/
        └── sql/
            └── init.sql
	    
Have the healenium authorization under init.sql:
   CREATE SCHEMA healenium AUTHORIZATION healenium_user;
   GRANT USAGE ON SCHEMA healenium TO healenium_user;

## Step 7: Create the docker-compose.yaml File

here is the steps to use Docker Compose to manage the deployment of infrastructure components using a docker-compose.yaml file located inside the `infra` folder.

 1. Navigate to the `infra` folder in your project directory.
 2. Create a new file named `docker-compose.yaml`.

## Step 8: Start Docker and Execute the test with Healenium Enabled

   1. Open cmd/terminal and navigate to infra folder containing file named `docker-compose.yaml`
   2. Run command "docker-compose up -d"

You can verify docker container running in docker desktop
![image](https://github.com/somyasinha15/HealeniumWebDocker/assets/93726730/69dc196e-bdd2-4d3c-8408-4a24b02e11e8)

## Step 9: Execute your Test and find selector details

Your test will now have self healing capability after first run, even if the locators changes in the sencond or any other consecutive run the execution will not fail.

Locator detail can be seen http://localhost:7878/healenium/selectors/

![image](https://github.com/somyasinha15/HealeniumWebDocker/assets/93726730/46255298-bd36-4dff-95df-ee3cca04bf6b)

