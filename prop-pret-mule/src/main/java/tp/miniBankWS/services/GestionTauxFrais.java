package tp.minibankWS.services;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface GestionTauxFrais {
   public double getTauxInteretCourant(@WebParam(name="nb_mois")int nb_mois);
   public double getFraisDossier(@WebParam(name="montant")double montant);
}
