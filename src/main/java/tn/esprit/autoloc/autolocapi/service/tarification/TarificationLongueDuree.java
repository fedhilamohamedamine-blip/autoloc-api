package tn.esprit.autoloc.autolocapi.service.tarification;

import org.springframework.stereotype.Component;

@Component("longueduree")
public class TarificationLongueDuree implements StrategieTarification {

    @Override
    public double calculer(double tarifJournalier, int jours) {
        return tarifJournalier * jours * 0.80;
    }
}
