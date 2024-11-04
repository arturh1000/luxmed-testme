# Getting Started

### - POST request json-struct 
    {
        "name" : "Name101",
        "departments": [{
            "name": "Dep01",
            "teams": [{
                "name": "alfa",
                "project": {
                    "manager": {
                        "email": "arturh1000@gmail.com"
                    }
                }
            }]
        }]
    }
### Run application plan:
- mvn package (project directory)
- cp .\target\luxmed-testme.war .\docker\ (windows)
- docker-compose -f .\docker\compose.yaml up (check before launch you not have `docker-web` container or image)
- rest point are available on http://localhost:8080/luxmed-testme/company