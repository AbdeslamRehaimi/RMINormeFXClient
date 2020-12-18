package Serveur;

import java.rmi.RemoteException;

public interface ServClientPerv extends ServClient {
    double calculeNormeVecteur(double x, double y) throws RemoteException; 
}
