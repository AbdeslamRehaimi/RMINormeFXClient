package Serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServClient extends Remote{
    double calculeNormeVecteur(double x, double y) throws RemoteException;
}
