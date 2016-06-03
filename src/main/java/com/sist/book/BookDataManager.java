package com.sist.book;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


import java.io.*;
import java.net.*;

/*
* String[] feel = {
						"사랑","로맨스","매력","즐거움","스릴",
						"소름","긴장","공포","유머","웃음","개그",
						"행복","전율","경이","우울","절망","신비",
						"여운","희망","긴박","감동","감성","휴머니즘",
						"자극","재미","액션","반전","비극","미스테리",
						"판타지","꿈","설레임","흥미","풍경","일상",
						"순수","힐링","눈물","그리움","호러","충격","잔혹"
};

String[] genre = {
	"드라마","판타지","공포","멜로","애정",
	"로맨스","모험","스릴러","느와르","다큐멘터리",
	"코미디","미스터리","범죄","SF","액션","애니메이션"
};
*/
@Component
public class BookDataManager {
	   public List<BookVO> bookAllData()
	   {
		   List<BookVO> list=new ArrayList<BookVO>();
		   try
		   {
			   Document doc=Jsoup.connect("http://www.bandinlunis.com/front/display/listBest.do").get();
			   Elements posterElem=doc.select("div.prod_thumb_img img");
			   Elements reviewElem=doc.select("dl.prod_info dd.txt_bex");
			   Elements titleElem =doc.select("dl.prod_info dt a");
			   Elements opriceElem =doc.select("dd.mt5 span.txt_reprice");
			   Elements spriceElem =doc.select("dd.mt5 span.txt_price strong em");
			   Elements pubElem = doc.select("dl.prod_info dd.txt_block");
			   for(int i=0; i<posterElem.size();i++)
			   {
				   Element pelem = posterElem.get(i);
				   String poster = pelem.attr("src");
				   Element relem = reviewElem.get(i);
				   Element telem = titleElem.get(i);
				   Element oelem = opriceElem.get(i);
				   Element selem = spriceElem.get(i);
				   Element pubelem = pubElem.get(i);
				   String temp = pubelem.text();
				   StringTokenizer st = new StringTokenizer(temp, "|");
				   BookVO vo = new BookVO();
				   vo.setNo(i);
				   vo.setPoster(poster);
				   vo.setReview(relem.text());
				   vo.setTitle(telem.text());
				   vo.setOprice(Integer.parseInt(oelem.text()
						   .substring(0, oelem.text().lastIndexOf("원")).replace(",", "")));
				   vo.setSprice(Integer.parseInt(selem.text().replace(",", "")));
				   vo.setAuthor(st.nextToken().trim());
				   vo.setPublisher(st.nextToken().trim());
				   vo.setRegdate(st.nextToken().trim());
				   list.add(vo);   
			   }
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   return list;
	   }
	   public BookVO bookDetail(int no)
	   {
		   List<BookVO> list=new ArrayList<BookVO>();
		   try
		   {
			   Document doc=Jsoup.connect("http://www.bandinlunis.com/front/display/listBest.do").get();
			   Elements posterElem=doc.select("div.prod_thumb_img img");
			   Elements reviewElem=doc.select("dl.prod_info dd.txt_bex");
			   Elements titleElem =doc.select("dl.prod_info dt a");
			   Elements opriceElem =doc.select("dd.mt5 span.txt_reprice");
			   Elements spriceElem =doc.select("dd.mt5 span.txt_price strong em");
			   Elements pubElem = doc.select("dl.prod_info dd.txt_block");
			   for(int i=0; i<posterElem.size();i++)
			   {
				   Element pelem = posterElem.get(i);
				   String poster = pelem.attr("src");
				   Element relem = reviewElem.get(i);
				   Element telem = titleElem.get(i);
				   Element oelem = opriceElem.get(i);
				   Element selem = spriceElem.get(i);
				   Element pubelem = pubElem.get(i);
				   String temp = pubelem.text();
				   StringTokenizer st = new StringTokenizer(temp, "|");
				   BookVO vo = new BookVO();
				   vo.setNo(i);
				   vo.setPoster(poster);
				   vo.setReview(relem.text());
				   vo.setTitle(telem.text());
				   vo.setOprice(Integer.parseInt(oelem.text()
						   .substring(0, oelem.text().lastIndexOf("원")).replace(",", "")));
				   vo.setSprice(Integer.parseInt(selem.text().replace(",", "")));
				   vo.setAuthor(st.nextToken().trim());
				   vo.setPublisher(st.nextToken().trim());
				   vo.setRegdate(st.nextToken().trim());
				   list.add(vo);   
			   }
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
		   return BookVO;
	   }
	   public String book_review(String title, int page) {
			StringBuffer sb = new StringBuffer();
			try {
				String key = "7b429affa32c43e1adf62ad1eebb6928";
				String query = "https://apis.daum.net/search/blog?" + "apikey=" + key + "&result=20&output=json&pageno="
						+ page + "&q=" + URLEncoder.encode(title, "UTF-8");
				URL url = new URL(query);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				if (conn != null) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					String data = "";
					while (true) {
						data = in.readLine();
						if (data == null)
							break;
						sb.append(data + "\n");
					}
				}
				conn.disconnect();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			return sb.toString();
		}

		public void json_parse(String json) {
			try {
				JSONParser jc = new JSONParser();
				JSONObject jo = (JSONObject) jc.parse(json);
				JSONObject channel = (JSONObject) jo.get("channel");
				JSONArray item = (JSONArray) channel.get("item");
				String desc = "";
				for (int i = 0; i < item.size(); i++) {
					JSONObject obj = (JSONObject) item.get(i);
					String review = (String) obj.get("description");
					// System.out.println(review);
					desc += review + "\n";
				}
				desc = desc.replaceAll("[A-Za-z0-9]", "");
				desc = desc.replace("&", "");
				desc = desc.replace(".", "");
				desc = desc.replace("#", "");
				desc = desc.replace("?", "");
				desc = desc.replace("/", "");
				desc = desc.replace(";", "");
				desc = desc.replace("(", "");
				desc = desc.replace(")", "");
				desc = desc.replace("[", "");
				desc = desc.replace("]", "");
				desc = desc.replace("+", "");
				desc = desc.replace(",", "");
				desc = desc.replace("'", "");
				desc = desc.replace("~", "");
				// desc=desc.replace("+", "");
				// System.out.println(desc);
				FileWriter fw = new FileWriter(
						"/home/sist/bigdataStudy/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MapReduceBookProject/desc.txt",
						true);
				fw.write(desc);
				fw.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
}