package compass.cadastro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ConverterData {

	public LocalDate dataConvertida (String dataString) {
				
		String [] dataSeparada = dataString.split("/");
		
		LocalDate dataPadrao = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
	
		return dataPadrao;
	}
	
	public String dataBrasileira (LocalDate dataPadrao) {
		
		DateTimeFormatter dataBrasileira = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
		
		String dataNacional = dataPadrao.format(dataBrasileira);
		
		return dataNacional;
	}

}
