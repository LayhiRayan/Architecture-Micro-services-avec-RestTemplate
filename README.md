# TP 20 — Architecture microservices avec RestTemplate

Ce dépôt contient les quatre applications demandées : serveur Eureka, service Client, Spring Cloud Gateway et service Car. Chaque application est un projet Maven autonome en Java 17.

## Prérequis

- JDK 17 (ou plus récent compatible)
- MySQL en écoute sur `localhost:3306`
- Maven (facultatif : le wrapper `mvnw.cmd` est fourni dans le projet Eureka)

Les bases `clientservicedb` et `carservicedb` sont créées automatiquement par MySQL Connector si l'utilisateur MySQL en a le droit. Les identifiants par défaut sont `root` et un mot de passe vide. Au besoin, définir `DB_USERNAME` et `DB_PASSWORD` dans l'environnement du terminal.

## Démarrage

Depuis la racine du dépôt, ouvrir quatre terminaux et exécuter dans cet ordre :

```powershell
cd eureka-server
mvn spring-boot:run
```

```powershell
cd client-service
mvn spring-boot:run
```

```powershell
cd car-service
mvn spring-boot:run
```

```powershell
cd gateway-service
mvn spring-boot:run
```

Le tableau de bord Eureka est disponible sur http://localhost:8761. Les trois clients y apparaissent après leur démarrage.

## Endpoints

| Application | Port | Endpoints principaux |
|---|---:|---|
| Eureka | 8761 | tableau de bord `/` |
| Client | 8081 | `GET /api/client`, `GET /api/client/{id}`, `POST /api/client` |
| Car | 8082 | `GET /api/car`, `GET /api/car/{id}`, `POST /api/car` |
| Gateway | 8888 | `/{ID-SERVICE}/api/...` |

Exemple de création d'un client :

```http
POST http://localhost:8081/api/client
Content-Type: application/json

{"nom":"Amine","age":25}
```

Exemple de création d'une voiture (remplacer `client_id` par l'identifiant d'un client existant) :

```http
POST http://localhost:8082/api/car
Content-Type: application/json

{"brand":"Dacia","model":"Logan","matricule":"12345-A-6","client_id":1}
```

Pour suivre le routage par la Gateway avec les noms de services du TP :

- `http://localhost:8888/SERVICE-CLIENT/api/client`
- `http://localhost:8888/SERVICE-CAR/api/car`

La Gateway utilise les routes de découverte fournies par Spring Cloud Gateway. Le service Car appelle le service Client avec `RestTemplate` pour inclure le client dans les réponses de lecture. Les DTO côté voiture gardent les champs `name` et `age` tels que montrés dans le TP ; le service Client, lui, expose `nom` et `age` comme dans la consigne.

## Notes de version

Le squelette fourni avait Spring Boot 4.x et Java 21. Le TP demande Java 17, et la version 4.x n'est pas la base compatible avec la ligne Spring Cloud du TP. Les projets sont donc alignés sur Spring Boot 3.5.6 et Spring Cloud 2025.0.0, avec les starters Eureka/Gateway correspondants.
