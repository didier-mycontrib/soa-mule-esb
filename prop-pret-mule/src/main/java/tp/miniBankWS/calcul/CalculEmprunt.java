package tp.miniBankWS.calcul;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CalculEmprunt {
	
	public double getMensualite(
			@WebParam(name="montant") double montant,
			@WebParam(name="nb_mois") int nb_mois,
			@WebParam(name="taux_mens_pct") double taux_mens_pct
	);

}
