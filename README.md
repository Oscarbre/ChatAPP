## Introduction

ChatApp est une simple application de messagerie permettant de communiquer avec un groupe, en se connectant à un serveur via un socket. 

## Structure

L'espace de travail contient 2 dossiers : `src` qui contient les fichiers sources et `bin` qui contient les fichiers compilés générés en conséquence. 
Le dossier `src` contient plusieurs classes pouvants être séparés en deux groupes :

Coté Serveur :
- `Server`: contient la logique du serveur et la fonction Main permettant de démarrer le serveur.
- `ClientHandler`: une instance de cette classe est créée pour chaque nouveau client qui se connecte au serveur. Elle s'occupe de rediriger les messages vers les autres clients.

Coté Client :
- `Client`: contient la logique client et les fonctions permettant d'envoyer/d'écouter les messages.
- `GUI` : contient la logique de l'interface graphique et la fonction Main permettant de démarrer une instance de client.
- `Controller` : joue le role de mediateur entre l'interface graphique et la logique métier du client.

## Architecture

Architecture verticale :

     GUI
      |
  Controller
      |
    Client
      :
      :
ClientHandler
      |
    Server
