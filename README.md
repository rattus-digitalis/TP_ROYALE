# Système de Gestion de Restaurants

## Description

Ce projet est une application Java permettant de gérer plusieurs restaurants avec leurs employés, leurs menus et leurs commandes.  
L'objectif est d'offrir un système de gestion efficace et intuitif pour administrer les informations des employés et des plats, tout en permettant de prendre et suivre les commandes des clients.

## Fonctionnalités principales

- **Gestion des restaurants** avec identifiant unique  
- **Gestion des employés** (serveurs, managers de salle, cuisiniers)  
- **Gestion des menus et des plats**  
- **Prise et suivi des commandes**  
- **Sauvegarde et chargement des informations** depuis des fichiers  
- **Interface utilisateur interactive** via le terminal  

## Contraintes techniques

- Implémentation en **Java** sans utilisation de bibliothèques externes (`.jar`)  
- Utilisation des classes `File`, `BufferedReader` et `BufferedWriter` pour la gestion des fichiers  
- Modélisation avec les classes suivantes :  
  - `Employe`  
  - `Plat`  
  - `Menu`  
  - `Commande`  
  - `Restaurant`  
