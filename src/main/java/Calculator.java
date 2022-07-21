import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class Calculator {
    private static final String FILE = "exchange_rates.xml";
    private Map<String, String> ratesExchange;

    public Calculator() {
        this.ratesExchange = getRates();
    }

    public Map<String, String> getRatesExchange() {
        return ratesExchange;
    }

    private static Map<String, String> getRates() {
        Map<String, String> rates = new HashMap<>();
        try {
            File file = new File(FILE);
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);

            List<Node> nodes =  document.selectNodes("//*[@currency]");
            for (Node node : nodes) {
                String currency = ((Element) node).attributeValue(("currency"));
                String rate = ((Element) node).attributeValue("rate");
                rates.put(currency, rate);
            }
        } catch (DocumentException e) {
            System.out.println("Reading file exception");
            e.printStackTrace();
        }
        return rates;
    }

    private String getExchangeRate(String currency) {
        if(currency == null) {
            System.out.println("Currency is null. Please try again.");
            throw new IllegalArgumentException("Currency is null");
        }

        String rate = null;
        for (Map.Entry<String, String> entry : ratesExchange.entrySet()) {
            if (entry.getKey().equals(currency.toUpperCase())) {
                rate = entry.getValue();
            }
        }
        return rate;
    }

    private static BigDecimal parseToBDecimal(String rate) {
        if (rate == null) {
            System.out.println("Incorrect number format. Please try again.");
            throw new IllegalArgumentException("Currency not found");
        }
        try {
            return new BigDecimal(rate);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number format. Please try again.");
            return new BigDecimal(0);
        }
    }

    public String convertCurrency(String inputAmount, String targetCurrency) {
        String result = "0.00";
        try {
            BigDecimal inputAmountBD = parseToBDecimal(inputAmount);
            String targetRate = getExchangeRate(targetCurrency);
            BigDecimal targetRateBD = parseToBDecimal(targetRate);
            result = inputAmountBD.multiply(targetRateBD).toString();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}
