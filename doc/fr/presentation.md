# Nom du projet
Le projet s'appelle A.

# Description du projet

Le projet A est un projet de développement Java **multi-applications** qui repose entièrement sur un **framework minimaliste** (le framework a). Son objectif est de permettre le développement d'applications d'une manière évolutive et flexible, en organisant le code source en composants réutilisables.

# Organisation des packages

Le code source du projet A s'organise en 4 packages racine dont nous allons détailler le rôle juste après :
- a.framework
- a.core
- a.entity
- a.config

### a.framework

Ce package contient les 16 classes Java qui composent le framework a.

Il regroupe 14 interfaces :
- les 11 interfaces fonctionnelles B, E, F, G, H, I, P, R, S (appelées caractéristiques).
- l'interface Entity, qui définit le composant entité
- l'interface Service, qui hérite des 11 caractéristiques
- l'interface Manager qui permet d'interfacer les composants

Ainsi que 2 implémentations :
- S1 : l'implémentation par défaut de S
- Outside : Classe statique qui fournit aux entités une implémentation de l'interface Manager

Le code source du framework est définitivement figé, ce qui garantit une compatibilité structuelle pour tous les code sources qui en découlent (contenus dans les packages *a.core*, *a.entity* et *a.config*).

### a.core

Ce package regroupe les différents noyaux applicatifs (core) se basant sur le framework a.
On notera que chaque core dispose de sa propre classe Main exécutable et peut a priori être utilisé pour batir une multitude d'applications différentes. Le code source de ce package doit se conformer à différentes règles de codage qui seront exposées par la suite.

### a.entity

Ce package regroupe les nombreuses entités (entity), sortes de briques fonctionnelles basées sur le framework a.
Son code source doit se conformer à différentes règles de codage qui seront exposées par la suite.

### a.config

Ce package regroupe les différentes configurations des applications se basant sur le framework a. Chaque configuration contient des fichiers de paramétrage et éventuellement des resources internes (images, icons, librairies tierce...) utilisées par l'application.
Là encore, ces fichiers sont organisés selon différentes règles exposées par la suite.

# Règles de nommage

### Un projet multi développeurs

Chaque développeur (ou instance de développement) souhaitant contribuer au projet A en y ajoutant son propre code source devra se conformer à certaines règles de nommage garantissant notamment une séparation nette des différentes contributions. Un identifiant unique \<dev-id\> sera attribué à chacun permettant d'obtenir des sous-répertoires racines distincts à l'intérieur des 3 packages : *a.core*, *a.entity*, *a.config*

Les deux premiers identifiants attribués sont gus (mon identifiant) et tav (identifiant de test). Nous avons ainsi les packages suivants :

Pour le développeur gus :
- a.core.gus
- a.entity.gus
- a.config.gus

Pour le développeur tav :
- a.core.tav
- a.entity.tav
- a.config.tav

En supposant que vous décidiez de rejoindre l'aventure avec l'identifiant marcus, vous auriez alors à disposition :
- a.core.marcus
- a.entity.marcus
- a.config.marcus

### Organisation pour les noyaux (core)

Les noyaux doivent suivre la convention de nommage suivante : *\<dev-id\>.<...>*
En tant que développeur marcus, tous vos noyaux doivent donc être nommés comme ceci : *marcus.<...>*

Si votre premier noyau s'appelle *marcus.kernel1*, alors son code source sera regroupé dans le package racine *a.core.marcus.kernel1*.
Il pourra contenir plusieurs classes réparties dans plusieurs packages (contrairement aux entités), mais devra contenir une unique classe Main (avec la méthode public static void main)

A titre d'exemple, j'ai développé un noyau nommé **gus.gyem** dont la classe Main est *a.core.gus.gyem.GyemMain*.
Si vous le souhaitez, vous pourrez utiliser ce noyau pour construire vos propres applications.
Dans cette optique, nous aborderons son fonctionnement par la suite.

### Organisation pour les entités (entity)

Mais bien plus qu'un noyau (vous pourrez toujours débuter en utilisant gus.gyem), je vous encourage à créer vos propres entités pour développer de nouvelles fonctionnalités. Les entités sont en effet de petits composants pouvant être combinés les uns aux autres pour obtenir des systèmes fonctionnels de complexité variable.

Les entités doivent suivre la convention de nommage suivante : *\<dev-id\>.<...>.<...>.<...>*
En tant que développeur marcus, toutes vos entités doivent être nommées comme ceci : *marcus.<...>.<...>.<...>* 
ou la partie <...>.<...>.<...> (sous-package) vous permet de ranger toutes vos entités selon vos propres règles.

De manière similaire aux noyaux, le nom du composant détermine le package accueillant le code source.
Une entité *marcus.conversion1* sera ainsi stockée dans le package *a.entity.marcus.conversion1*

Le code source d'une entité est composée d'une ou plusieurs classes Java situées à la racine de son package.
L'une de ces classes doit impérativement être nommée *EntityImpl*. Il s'agit de la classe principale de l'entité.
On notera que dans la grande majorité des cas, le code source de l'entité se réduit à cette seule classe.

Voici quelques exemples d'entités que j'ai déjà développé :
- a.execute.exception
- b.entitysrc1.generator1
- b.menu1.init

### Organisation pour les configurations (config)

Nous avons vu que les noms des composants core et entity débutent impérativement par l'identifiant du développeur qui en est l'auteur.
Mais cette règle s'applique aussi pour les nommages d'applications, lesquels doivent suivre la convention : *\<dev-id\>.<...>*

Une application est composée d'un unique noyau, d'une multitudes d'entités (a priori), et d'une unique configuration.
Celle-ci est donc liée à la fois à l'application et au noyau. Son package racine sera donc le suivant :
*a.config.\<core-name\>.\<appli-name\>*

Pour fixer les idées, imaginons le cas de deux développeurs john et smith qui ont chacun créé leurs propres noyaux, respectivement john.core1 et smith.core1, et souhaitent chacun créer deux applications en utilisant les 2 noyaux.

Alors john va devoir créer les deux packages suivants :
- a.config.john.core1.john.appli1
- a.config.smith.core1.john.appli2

Et smith les deux packages suivants :
- a.config.smith.core1.smith.appli1
- a.config.john.core1.smith.appli2

Nous verrons par la suite qu'un paramètre passé au démarrage du noyau (ou un éventuel fichier *param* situé à la racine *a.config.\<core-name\>*) permet d'orienter ce dernier vers l'une au l'autre des configurations disponibles.

# Règles de codage

### Restrictions sur les imports

Afin d'assurer une stricte séparation entre les différents composants core et entity, aucune classe d'un composant ne pourra être directement importée dans le code source d'un autre composant.

Dans le cas d'une entité notamment, le seul import débutant par a.<...> qui est autorisé dans ses classes est :
*import a.framework.\*;*

### Coder une classe d'entité

La classe principale d'une entité doit toujours respecter les règles suivantes :
- Etre nommée *EntityImpl*
- Implémenter l'interface *a.framework.Entity*
- Avoir une méthode *creationDate()* qui renvoie la date de création de l'entité sous la forme d'une String au format yyyyMMdd

Pour être utilisée directement par le noyau ou indirectement par d'autres entités, elle devra par ailleurs implémenter une ou plusieurs des 11 interfaces de caractérisation definies par le framework : E, B, F, G, P, T, H, R, V, S, I, ou même étendre l'implémentation par défaut de S : S1.

Nous aurons l'occasion de découvrir ce que ces règles donnent en pratique lorsque nous étudierons des exemples.

### Coder un noyau

Il est extrêmement simple de créer des entités et de les utiliser pour construire une application en partant d'un noyau déjà existant. Coder un noyau, c'est un peu plus compliqué. Si cela vous tente néanmoins, je vous encourage à regarder le code source de gus.gyem. N'hésitez pas à me contacter pour échanger à ce sujet.

### Et la config ?

Une config ne contient que du paramétrage et des resources internes. Cette partie n'est par conséquent pas concernée par les règles de codage. Elle se conforme néanmoins à un certain nombre de règles qui sont entièrement dictées par le noyau dont elle dépend.

# Utiliser le noyau gus.gyem

### Lancer une application basée sur gus.gyem

Lorsqu'on lance la classe *a.core.gus.gyem.GyemMain* que se passe-t-il ? Vous pouvez essayer et vous verrez apparaître une fenêtre dont le titre est "Application" et dont le panneau central affichage le message suivant :

- app.maingui not found inside application properties
- core name = gus.gyem
- core build = 20210805

Il s'agit là du comportement par défaut du noyau *gus.gyem* (nous y reviendrons), et si nous ne gardaions dans le projet que son propre code ainsi que celui du framework, nous aurions le même résultat.

Lorsqu'il existe une ou plusieurs configurations applicatives dans le package *a.config*, comment pouvons-nous alors indiquer au noyau laquelle choisir ? Il existe deux moyens.

En le précisant sous forme de paramètre externe :
- pour cela, il faut ajouter en argument, au lancement de l'application, la ligne suivante : *config=\<config-name\>*
(s'il y a plusieurs arguments externes, ceux-ci seront séparés par des points-virgule)

En me précisant sous forme de paramètre interne :
- pour cela, il faut créer le fichier properties suivant *a.config.gus.gyem.param* et y ajouter la ligne *config=\<config-name\>*

Ce fichier param ne fait pas officiellement parti du projet A, il s'agit juste d'un fichier qu'on ajoute au code source d'une application pour indiquer quelle est sa configuration.

Vous pouvez ainsi tester les différentes configurations existantes en ajoutant la ligne *config=\<config-name\>*, soit dans le fichier param, soit en ligne argument passée au lancement.

Au moment ou j'écris ces lignes, les configurations présentes dans le projet sont les suivantes :
- gus.test1cust
- gus.test2fatal
- gus.test3error
- gus.test4orange
- gus.test5mapping
- gus.appli1


### Les propriétés de l'application

A la racine de chaque configuration applicative (comme par exemple *a.config.gus.test1cust*), nous avons un fichier properties *prop* qui regroupe toutes les propriétés de l'application.
