package tn.esprit.autoloc.autolocapi.service.tarification;

import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TarificationService {

    private final Map<String, StrategieTarification> strategies;

    public TarificationService(Map<String, StrategieTarification> strategies) {
        this.strategies = Map.copyOf(strategies);
    }

    public double calculer(String politique, double tarifJournalier, int jours) {
        if (!Double.isFinite(tarifJournalier) || tarifJournalier <= 0) {
            throw new IllegalArgumentException("Le tarif journalier doit être strictement positif.");
        }
        if (jours <= 0) {
            throw new IllegalArgumentException("Le nombre de jours doit être strictement positif.");
        }
        if (politique == null || politique.isBlank()) {
            throw new IllegalArgumentException("La politique de tarification est obligatoire.");
        }

        String cle = politique.trim().toLowerCase(Locale.ROOT);
        StrategieTarification strategie = strategies.get(cle);
        if (strategie == null) {
            String politiquesDisponibles = strategies.keySet().stream()
                    .sorted()
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException(
                    "Politique de tarification inconnue : " + politique
                            + ". Politiques disponibles : " + politiquesDisponibles);
        }

        double montant = strategie.calculer(tarifJournalier, jours);
        if (!Double.isFinite(montant)) {
            throw new IllegalArgumentException("Le montant calculé dépasse la plage numérique autorisée.");
        }
        return montant;
    }
}
