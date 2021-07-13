package ie.nuim.cs.cs402;

import javax.ws.rs.Path;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import binancePackage.BinanceConnection;
import models.CryptoLayout;

@Path("/crypto")
public class CryptoResource {
		
		private CryptoLayout getDate(String check, List<CryptoLayout> data) {
			for(int i=0; i<data.size(); i++) {
				CryptoLayout temp = data.get(i);
				if(check.equals(temp.symbol)) {
					return temp;
				}
			}
			return null;
		}
		@GET
		@Produces(MediaType.TEXT_HTML)
		public String sayHTMLHello() {
			List<CryptoLayout> data = new BinanceConnection().getPrices();
			CryptoLayout btc = getDate("BTCUSDT", data);
			CryptoLayout eth = getDate("ETHUSDT", data);
			CryptoLayout bnb = getDate("BNBUSDT", data);
			CryptoLayout xrp = getDate("XRPUSDT", data);
			CryptoLayout doge = getDate("DOGEUSDT", data);
			return "<html><table >\r\n"
					+ "    <tbody>\r\n"
					+ "        <tr>\r\n"
					+ "			   <td><b>Logo</b></td>\r\n"
					+ "			   <td><b>Name</b></td>\r\n"
					+ "			   <td><b>24hr Change</b></td>\r\n"
					+ "		   </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td><img src=\"https://s2.coinmarketcap.com/static/img/coins/64x64/1.png\" alt=\"Bitcoin\" style=\"width:48px;height:48px;\"></td>\r\n"
					+ "            <td>Bitcoin</td>\r\n"
					+ "            <td>" + btc.priceChange + "</td>\r\n"    
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td><img src=\"https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png\" alt=\"Bitcoin\" style=\"width:48px;height:48px;\"></td>\r\n"
					+ "            <td>Ethereum</td>\r\n"
					+ "            <td>" + eth.priceChange + "</td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td><img src=\"https://s2.coinmarketcap.com/static/img/coins/64x64/1839.png\" alt=\"Bitcoin\" style=\"width:48px;height:48px;\"></td>\r\n"
					+ "            <td>BNB</td>\r\n"
					+ "            <td>"+bnb.priceChange+"</td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td><img src=\"https://s2.coinmarketcap.com/static/img/coins/64x64/52.png\" alt=\"Bitcoin\" style=\"width:48px;height:48px;\"></td>\r\n"
					+ "            <td>Ripple</td>\r\n"
					+ "            <td>"+xrp.priceChange+"</td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td><img src=\"https://s2.coinmarketcap.com/static/img/coins/64x64/74.png\" alt=\"Bitcoin\" style=\"width:48px;height:48px;\"></td>\r\n"
					+ "            <td>Dogecoin</td>\r\n"
					+ "            <td>"+doge.priceChange+"</td>\r\n"
					+ "        </tr>\r\n"
					+ "    </tbody>\r\n"
					+ "</table></html>"
					;
		}

		@Path("/prices")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<CryptoLayout> getPrices() {
			return new BinanceConnection().getPrices();
		}
}
