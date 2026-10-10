# Application de Gestion Commerciale (JPA / Hibernate 5.6)

Projet Java de gestion commerciale mettant en œuvre une architecture en couches (Entities / DAO / Service), la persistance avec JPA / Hibernate 5.6, une base de données MySQL et des tests unitaires JUnit.

---

## 🏗️ Architecture du projet

Le projet respecte la structure standard Maven :

```text
gestion-commerciale/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── dao/          # GenericDAO (DAO générique JPA)
│   │   │   ├── entities/     # Categorie, Produit, Commande, CommandeProduit, User, Client, Fournisseur
│   │   │   └── service/      # CrudService et ProduitService
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml   # Configuration JPA / Hibernate
│   └── test/
│       └── java/org/example/
│           └── ProduitDaoTest.java   # Tests unitaires JUnit 4
├── images/                           # Captures d'écran du README
└── pom.xml
```

---

## 🚀 Fonctionnalités clés

### 1. Généricité (DAO & Service)
- `GenericDAO<T, ID>` : centralise les opérations CRUD (`save`, `findById`, `findAll`, `update`, `delete`) avec gestion des transactions JPA et fermeture automatique des `EntityManager`.
- `CrudService<T, ID>` : couche service générique qui encapsule le DAO.

### 2. Modélisation avancée des données
- **Héritage** : stratégie `@Inheritance(strategy = InheritanceType.JOINED)` pour l'entité abstraite `User` et ses classes filles `Client` et `Fournisseur`.
- **Relation N:N explicite** : entité `CommandeProduit` avec clé composée `@EmbeddedId` (`CommandeProduitPK`).
- **Contraintes & relations** : `@OneToMany`, `@ManyToOne`, contrainte `@Column(unique = true)` sur le nom de la catégorie.

### 3. Requêtes avancées (`ProduitService`)
- **Named Queries** : récupération des produits par catégorie via `@NamedQuery`.
- **Criteria API** : recherche dynamique multi-critères (libellé / mot-clé et tranche de prix), type-safe.

### 4. Tests unitaires (JUnit 4)
- Validation automatisée de la création d'entités, des relations, des Named Queries et des filtres Criteria API.

---

## 🛠️ Prérequis & technologies

| Élément | Version |
|---|---|
| Java JDK | 11 ou plus récent (testé sous JDK 21) |
| JPA / ORM | Hibernate 5.6.15.Final |
| Base de données | MySQL 8.x |
| Build | Apache Maven |
| Tests | JUnit 4.13.2 |

---

## ⚙️ Configuration & installation

1. **Base de données MySQL** : assurez-vous que le serveur MySQL est démarré sur le port `3306`. La base `gestion_commerciale` est créée automatiquement si elle n'existe pas (`createDatabaseIfNotExist=true`).

2. **Configuration `persistence.xml`** : ajustez le mot de passe dans `src/main/resources/META-INF/persistence.xml` selon votre installation locale :

   ```xml
   <property name="javax.persistence.jdbc.user" value="root"/>
   <property name="javax.persistence.jdbc.password" value="VOTRE_MOT_DE_PASSE"/>
   ```

3. **Exécution des tests** : depuis la racine du projet :

   ```bash
   mvn test
   ```

---

## 📸 Captures d'écran

Captures de l'exécution de `ProduitDaoTest` (console IntelliJ).

### 1. Génération automatique du schéma par Hibernate

Hibernate crée les tables `categories`, `clients`, `commande_produit`, `commandes`, `fournisseurs`, `produits` et `users`.

<img width="435" height="334" alt="Table1" src="https://github.com/user-attachments/assets/266ceeb6-e5aa-4f65-99c3-82b5ad954a94" />

<img width="462" height="343" alt="Table2" src="https://github.com/user-attachments/assets/48dd3c56-1ea0-4ccd-b5b4-d22a040d75a1" />

### 2. Contraintes et clés étrangères

Contrainte d'unicité sur le nom de la catégorie, et clés étrangères de l'héritage `JOINED` (`clients` / `fournisseurs` → `users`) ainsi que de la table d'association `commande_produit`.
<img width="460" height="317" alt="Table3" src="https://github.com/user-attachments/assets/42fe4f4e-c505-442c-b5b2-b370576aaf23" />

<img width="515" height="273" alt="Table4" src="https://github.com/user-attachments/assets/ac2230db-a626-45a5-94da-39f0ffca1e94" />


### 3. Insertion des données

<img width="484" height="331" alt="Table5" src="https://github.com/user-attachments/assets/01c18511-143b-4396-84bf-9461773902cd" />

### 4. Named Query : produits par catégorie

<img width="512" height="318" alt="Table6" src="https://github.com/user-attachments/assets/58896165-6ef2-494e-958b-9c79f9116749" />


### 5. Criteria API : recherche par mot-clé et prix minimum

Le test se termine avec `exit code 0` : tous les tests passent.

<img width="562" height="259" alt="Capture d&#39;écran 2026-10-10 154128" src="https://github.com/user-attachments/assets/cc0f3f31-bce6-4935-a667-dbeaac1f3b76" />

---

## 👤 Auteur
# OUADAY SARA
