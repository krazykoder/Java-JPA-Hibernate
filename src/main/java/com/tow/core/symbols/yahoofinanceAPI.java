package com.tow.core.symbols;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import yahoofinance.*;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

// Data Aggregation : 	https://github.com/sstrickx/yahoofinance-api
// Technical Analysis:	https://github.com/ta4j/ta4j
// 						https://github.com/ta4j/ta4j/blob/master/ta4j-examples/src/main/java/ta4jexamples/Quickstart.java

public class yahoofinanceAPI {

	@Test
	public void symbolHistory2() throws IOException {
		// TODO Auto-generated method stub
		Stock google = YahooFinance.get("GOOG");
		List<HistoricalQuote> googleHistQuotes = google.getHistory();

		System.out.println(googleHistQuotes.get(0));

	}

	public void symbolhistory() throws IOException {
		// TODO Auto-generated method stub
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -5); // from 5 years ago

		Stock google = YahooFinance.get("GOOG", from, to, Interval.DAILY);

		google.print();
	}

//	@Test
	public void listofsymbols() throws IOException {
		// TODO Auto-generated method stub
		String[] symbols = new String[] { "INTC", "BABA", "TSLA", "AMD", "QQQ" };
		Map<String, Stock> stocks = YahooFinance.get(symbols); // single request
		Stock intel = stocks.get("INTC");
		Stock amd = stocks.get("AMD");
		intel.print();
		amd.print();
	}

	@SuppressWarnings("unused")
	public void sampleFetch() throws IOException {
		Stock stock = YahooFinance.get("INTC");

		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal change = stock.getQuote().getChangeInPercent();
		BigDecimal peg = stock.getStats().getPeg();
		BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

		stock.print();
	}

}
