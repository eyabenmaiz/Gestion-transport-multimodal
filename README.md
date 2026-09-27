# \# Gestion d'une Société de Transport Multimodal

# 

# Mini-projet — \*Programmation Orientée Objets Java 2\*, ENET'Com, 2ème année GII, 2025-2026.

# 

# \## 📋 Description

# 

# Application Java permettant de gérer les cargaisons transportées par une société logistique (Routière, Aérienne, Maritime), chacune contenant plusieurs marchandises. L'application utilise :

# 

# \- des concepts orientés objet (héritage, polymorphisme, classes abstraites, exceptions),

# \- une interface graphique \*\*Swing\*\*,

# \- une base de données \*\*MySQL\*\* pour la persistance des cargaisons et marchandises.

# 

# \## 🏗️ Diagramme de classes simplifié

# 

# ```

# &#x20;                Cargaison (abstract)

# &#x20;                ---------------------

# &#x20;                - distance : double

# &#x20;                - marchandises : List<Marchandise>

# &#x20;                ---------------------

# &#x20;                + ajouterMarchandise()

# &#x20;                + afficherMarchandises()

# &#x20;                + consulterMarchandise(numero)

# &#x20;                + getVolumeTotal()

# &#x20;                + getPoidsTotal()

# &#x20;                + calculerCout() : abstract

# &#x20;                + getType() : abstract

# &#x20;                       △

# &#x20;       ┌───────────────┼───────────────┐

# &#x20;       │                │               │

# CargaisonRoutiere  CargaisonAerienne  CargaisonMaritime

# \------------------ ------------------ ------------------

# \+ calculerCout()   + calculerCout()   + calculerCout()

# \+ getType()        + getType()        + getType()

# 

# 

# &#x20;        Marchandise

# &#x20;        -----------------

# &#x20;        - numero : int

# &#x20;        - poids : double

# &#x20;        - volume : double

# 

# 

# &#x20;  MarchandiseIntrouvableException (extends Exception)

# ```

# 

# `Cargaison` possède une relation de \*\*composition\*\* avec `Marchandise` (une cargaison contient 0..N marchandises).

# 

# \## 📂 Structure du projet

# 

# ```

# src/

# ├── model/

# │   ├── Marchandise.java

# │   ├── Cargaison.java                  (classe abstraite)

# │   ├── CargaisonRoutiere.java

# │   ├── CargaisonAerienne.java

# │   ├── CargaisonMaritime.java

# │   └── MarchandiseIntrouvableException.java

# ├── dao/

# │   ├── ConnexionBD.java                (connexion JDBC/MySQL)

# │   ├── CargaisonDAO.java

# │   └── MarchandiseDAO.java

# ├── gui/

# │   └── MainFrame.java                  (interface graphique Swing)

# └── TestApp.java                        (application de test en console)

# 

# sql/

# └── transportdb.sql                     (script de création de la base)

# ```

# 

# \## 💰 Règles de calcul du coût

# 

# | Type | Condition | Formule |

# |---|---|---|

# | Routière | volume total < 380 000 | 4 × distance × poids total |

# | Routière | volume total ≥ 380 000 | 6 × distance × poids total |

# | Aérienne | volume total < 80 000 | 10 × distance × poids total |

# | Aérienne | volume total ≥ 80 000 | 12 × distance × poids total |

# | Maritime | poids total < 300 000 | 5 × distance × poids total |

# | Maritime | poids total ≥ 300 000 | 8 × distance × poids total |

# 

# \## 🗄️ Base de données MySQL

# 

# Base : `transportdb` — voir `sql/transportdb.sql` pour la création complète.

# 

# ```sql

# cargaison ( id INT PK AUTO, type VARCHAR(20), distance DOUBLE )

# marchandise ( id INT PK, poids DOUBLE, volume DOUBLE, idCargaison INT FK )

# ```

# 

# \### Configuration de la connexion

# 

# Modifiez si besoin l'utilisateur/mot de passe MySQL dans `src/dao/ConnexionBD.java` :

# 

# ```java

# private static final String URL = "jdbc:mysql://localhost:3306/transportdb?useSSL=false\&serverTimezone=UTC";

# private static final String UTILISATEUR = "root";

# private static final String MOT\_DE\_PASSE = "root";

# ```

# 

# \## 🚀 Mise en route (Eclipse)

# 

# \### 1. Créer la base de données

# 

# Importez le script `sql/transportdb.sql` dans MySQL Workbench (ou en ligne de commande) :

# 

# ```bash

# mysql -u root -p < sql/transportdb.sql

# ```

# 

# \### 2. Importer le projet dans Eclipse

# 

# `File → Import → Existing Projects into Workspace`, ou créer un nouveau projet Java et copier le dossier `src/` dedans.

# 

# \### 3. Ajouter le driver JDBC MySQL

# 

# Téléchargez le connecteur \*\*MySQL Connector/J\*\* (fichier `.jar`, ex: `mysql-connector-j-8.x.x.jar`) depuis \[dev.mysql.com](https://dev.mysql.com/downloads/connector/j/), puis :

# 

# `Clic droit sur le projet → Build Path → Configure Build Path → Libraries → Add External JARs...` et sélectionner le `.jar` téléchargé.

# 

# \### 4. Lancer l'application

# 

# \- \*\*Test console\*\* (sans interface, sans base) : exécuter `TestApp.java` → `Run As → Java Application`

# \- \*\*Interface graphique complète\*\* : exécuter `gui/MainFrame.java` → `Run As → Java Application`

# 

# \## 👥 Auteurs

# 

# Eya BEn Maiz - Projet réalisé en binôme



