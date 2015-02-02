package tp.proposition_pret;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PropPretService {
	
	public PropositionPret getPropPret(@WebParam(name="montant")double montant,@WebParam(name="nb_mois")int nb_mois);
	public PropositionPret getPropPretWithEmail(@WebParam(name="montant")double montant,@WebParam(name="nb_mois")int nb_mois,
												@WebParam(name="email")String email);
}
