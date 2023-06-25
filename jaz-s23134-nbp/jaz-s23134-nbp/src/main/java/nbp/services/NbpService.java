package nbp.services;

import nbp.model.Rate;
import nbp.model.Root;
import nbp.model.SaveData;
import nbp.repository.NbpRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NbpService {
    private RestTemplate restTemplate;
    private NbpRepository nbpRepository;

    public NbpService(RestTemplate restTemplate, NbpRepository nbpRepository) {
        this.restTemplate = restTemplate;
        this.nbpRepository = nbpRepository;
    }


    public Root getAvgList(String currency, String startData, String endData) {
        String url = String.format("https://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=json", currency, startData, endData);
        return restTemplate.getForObject(url, Root.class);
    }

    public double getAvgCurrency(String currency, String startData, String endData) {
        Root root = getAvgList(currency, startData, endData);
        List<Rate> rates = root.getRates();
        double amount = 0;
        for (Rate rat : rates) {
            amount += rat.getMid();
        }
        saveData(new SaveData(currency, startData, endData));
        return amount / rates.size();

    }

    //Zapisywanie do bazy
    public SaveData saveData(SaveData saveData) {
        return nbpRepository.save(saveData);
    }

}
