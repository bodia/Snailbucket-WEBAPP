package net.rwchess.site.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;

import chesspresso.game.Game;
import chesspresso.pgn.PGNReader;
import chesspresso.pgn.PGNSyntaxError;
import net.rwchess.site.data.DAO;
import net.rwchess.site.data.File;
import net.rwchess.site.utils.UsefulMethods;


/**
 * Used to import the preliminary data for once 
 */
public class Import extends HttpServlet {
	
	private static Map m;
	
	public static int getRatingFor(String username) {
		if (username.equalsIgnoreCase("roberttorma")) return 2189;
		if (username.equalsIgnoreCase("PeterSanderson")) return 2206;
		if (username.equalsIgnoreCase("Maras")) return 2162;
		if (username.equalsIgnoreCase("Karima")) return 2175;
		if (username.equalsIgnoreCase("iwulu")) return 2172;
		if (username.equalsIgnoreCase("AIDog")) return 2086;
		if (username.equalsIgnoreCase("zalik")) return 2140;
		if (username.equalsIgnoreCase("Pallokala")) return 2066;
		if (username.equalsIgnoreCase("jussu")) return 1941;
		if (username.equalsIgnoreCase("ivohristov")) return 1961;
		if (username.equalsIgnoreCase("WilkBardzoZly")) return 1734;
		if (username.equalsIgnoreCase("sangalla")) return 1975;
		if (username.equalsIgnoreCase("NatIN")) return 1875;
		if (username.equalsIgnoreCase("AlesD")) return 1879;
		if (username.equalsIgnoreCase("Acho")) return 1819;
		if (username.equalsIgnoreCase("NoiroP")) return 1815;
		if (username.equalsIgnoreCase("bodzolca")) return 1742;
		if (username.equalsIgnoreCase("Bodia")) return 1735;
		if (username.equalsIgnoreCase("HerrGott")) return 1658;
		if (username.equalsIgnoreCase("pchesso")) return 1697;
		if (username.equalsIgnoreCase("SachinRavi")) return 1624;
		if (username.equalsIgnoreCase("Gavrilo")) return 1644;
		if (username.equalsIgnoreCase("piorgovici")) return 1581;
		if (username.equalsIgnoreCase("lutom")) return 1539;
		if (username.equalsIgnoreCase("wfletcher")) return 1533;
		if (username.equalsIgnoreCase("Nitreb")) return 1269;
		else return 0;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		
	/*	String[] names = { "PeterSanderson", "iwulu", "Maras", "Gregorioo",
				"ivohristov", "AlesD", "Acho", "NoiroP", "bodzolca", "Bodia",
				"WilkBardzoZly", "pchesso", "Gavrilo", "sachinravi", "piorgovici",
				"wfletcher", "Nitreb"}; */
		/*
            DatastoreService datastore =
DatastoreServiceFactory.getDatastoreService();
            Query query = new Query("_ah_SESSION");
            PreparedQuery results = datastore.prepare(query);

            res.getOutputStream().println("Deleting " + results.countEntities() + " sessions from data store");

            for (Entity session : results.asIterable()) {
                    datastore.delete(session.getKey());
            }
    */
		
		String toCommit = "[Event \"RW Swiss 2010\"]\n[Site \"FICS\"]\n[Date \"2010.03.28\"]\n[Round \"1\"]\n[White \"iwulu\"]\n[Black \"pchesso\"]\n[Result \"1-0\"]\n[WhiteElo \"2171\"]\n[BlackElo \"1720\"]\n[ECO \"A09\"]\n1. Nf3 d5 2. c4 d4 3. g3 c5 4. Bg2 Nc6 5. O-O e5 6. d3 Nf6 7. Nbd2 Be7 8. a3 a5 9. Rb1 O-O 10. Ne1 Qc7 11. Nc2 Nd7 12. e3 Ra6 13. exd4 cxd4 14. b4 axb4 15. axb4 Nd8 16. Na3 b6 17. Nb5 Qb8 18. f4 Ne6 19. Ne4 f5 20. Ng5 Nxg5 21. fxg5 Bb7 22. Qe2 Ra4 23. Nxd4 Bxg2 24. Qxg2 exd4 25. Qd5+ Rf7 26. Rxf5 Nf6 27. gxf6 Bxf6 28. Bg5 Qe8 29. Rbf1 Kh8 30. Bxf6 gxf6 31. Qxd4 {Black resigns} 1-0\n\n[Event \"RW Swiss 2010\"]\n[Site \"FICS\"]\n[Date \"2010.03.25\"]\n[Round \"1\"]\n[White \"bodzolca\"]\n[Black \"FriendshipFighter\"]\n[Result \"0-1\"]\n[WhiteElo \"1759\"]\n[BlackElo \"2128\"]\n[ECO \"A43\"]\n1. e4 d6 2. d4 g6 3. Nc3 Bg7 4. Nf3 c5 5. d5 Nf6 6. Bd3 O-O 7. O-O e6 8. Bg5 Na6 9. Qd2 Nc7 10. Bc4 exd5 11. exd5 Bg4 12. Nh4 a6 13. a4 b6 14. h3 Bd7 15. Rfe1 b5 16. axb5 axb5 17. Rxa8 Qxa8 18. Qf4 bxc4 19. Bxf6 Nxd5 20. Nxd5 Qxd5 21. Re7 Bc6 22. Nf3 h6 23. Bxg7 Kxg7 24. Qd2 Qxd2 25. Nxd2 Kf6 26. Rc7 Bd5 27. Ra7 Rb8 28. Ra6 Ke6 {White resigns} 0-1\n\n[Event \"RW Swiss 2010\"]\n[Site \"FICS\"]\n[Date \"2010.03.21\"]\n[Round \"1\"]\n[White \"Gregorioo\"]\n[Black \"Bodia\"]\n[Result \"1-0\"]\n[WhiteElo \"2072\"]\n[BlackElo \"1673\"]\n[ECO \"A36\"]\n1. c4 c5 2. g3 g6 3. Bg2 Bg7 4. Nc3 Nc6 5. d3 d6 6. h4 a6 7. h5 Nh6 8. Bg5 f6 9. Bd2 Rg8 10. hxg6 Nf5 11. e4 Nfd4 12. gxh7 Rh8 13. Qh5+ Kd7 14. Nd5 e6 15. Qf7+ {Black resigns} 1-0\n\n[Event \"RW Swiss 2010\"]\n[Site \"FICS\"]\n[Date \"2010.03.20\"]\n[Round \"1\"]\n[White \"SachinRavi\"]\n[Black \"Madmansreturn\"]\n[Result \"0-1\"]\n[WhiteElo \"1636\"]\n[BlackElo \"2004\"]\n[ECO \"B17\"]\n1. e4 c6 2. d4 d5 3. Nc3 dxe4 4. Nxe4 Nd7 5. Nf3 Ngf6 6. Bd3 Nxe4 7. Bxe4 Nf6 8. Bd3 Bg4 9. Be3 e6 10. O-O Bd6 11. h3 Bh5 12. Be2 Qc7 13. c4 O-O 14. c5 Bf4 15. Bxf4 Qxf4 16. Re1 Rfd8 17. Qb3 Rab8 18. Qa4 Bxf3 19. Bxf3 Rxd4 20. Qxa7 Ra4 21. g3 Rxa7 22. gxf4 Ra4 23. f5 Rf4 24. Bh1 Rxf5 25. b4 Rd8 26. Rac1 Rd2 27. Rcd1 Rfxf2 28. Bf3 Kf8 29. Be2 Rfxe2 30. Rxe2 Rxd1+ 31. Kf2 Nd5 32. Re4 Rd2+ 33. Kf3 Rxa2 34. Rh4 Ra3+ 35. Ke4 Re3+ 36. Kd4 h6 37. Rh5 e5+ 38. Rxe5 Rxe5 39. Kxe5 {White resigns} 0-1\n\n[Event \"RW Swiss 2010\"]\n[Site \"FICS\"]\n[Date \"2010.03.27\"]\n[Round \"1\"]\n[White \"sangalla\"]\n[Black \"Nitreb\"]\n[Result \"1-0\"]\n[WhiteElo \"1893\"]\n[BlackElo \"1244\"]\n[ECO \"D36\"]\n1. d4 d5 2. c4 e6 3. Nc3 Be7 4. Nf3 Nf6 5. Bg5 O-O 6. e3 c6 7. Qc2 Nbd7 8. cxd5 exd5 9. Bd3 h6 10. Bh4 Re8 11. O-O Nf8 12. Rab1 Bg4 13. Ne5 Be6 14. b4 N6d7 15. Bxe7 Qxe7 16. Nxd7 Nxd7 17. b5 c5 18. dxc5 Nxc5 19. Rfd1 Red8 20. Ne2 Rac8 21. Qd2 Ne4 22. Bxe4 dxe4 23. Nd4 Bc4 24. Qb2 Bd3 25. Rbc1 Qd7 26. h3 Qd5 27. a4 Rc4 28. Rxc4 Qxc4 29. Rc1 Qd5 30. Qb4 Qd7 31. Qc5 a6 32. Qc7 axb5 33. axb5 Kf8 34. b6 Ba6 35. Nf5 Qd5 36. Rc5 Qd7 37. Qxd7 Rxd7 38. Rc8+ 1-0";
		
			PersistenceManager pm = DAO.get().getPersistenceManager();
		try {
			File fl = (File) pm.getObjectById(File.class,
					"swiss2010.pgn");
			fl.setFile(new Blob(toCommit.getBytes()));
		} 
		catch (JDOObjectNotFoundException e) {
			
		}
		finally {
			pm.close();
		}
		
		
		/**PersistenceManager pm = DAO.get().getPersistenceManager();
		String[] names = { "HerrGott", "Noiro", "piorgovici", "pchesso",
				"Bodia", "Acho", "sachinravi", "jussu", "Natin", "Nitreb",
				"roberttorma", "WilkBardzoZly", "iwulu", "wfletcher", "ivohristov",
				"Maras", "AlesD", "exray", "Gavrilo", "Pallokala", "bodzolca",
				"sangalla" };
		String[] countries = { "ro", "sk", "ro", "de", "ua", "ar", "in", "ee",
				"no", "ca", "hu", "pl", "ng", "za", "bg", "lt", "cz", "" +
						"ca", "cs", "fi", "si", "id" };
		String[] passwords = { "283ffefecd9c77eaac17eb510e0d0fde",
				"c098a4d9bb9516a951b7b510a76418b4",
				"c5258d384b2c9395cc56d0fa9f481306",
				"ce321c24dc777c81666271b4b78bc063",
				"44553e42030473c29b270fe3b1f728be",
				"a28710fcf793cd2374ac0c081e5c3f7d",
				"99693a548357e4b089837816c182a500",
				"d63d20e7ee8cc0dcfd68c038274945a2",
				"098f6bcd4621d373cade4e832627b4f6",
				"801d1b0502f760db02b6e690b0037430",				
				"968b18793e56cbea70692fba31189ae7",
				"3228d24e2ccc9443e82e58d5008c50f3",
				"10ce72c6b816ac8b25b062ebae2108ae",
				"74790f436b9dc6ae4d47bfb6c924d3ad",
				"95cbc4d8d2c2864de256fc08ce23d8c1",
				"7476ed9af142c6fe337846f0c5ac466b",
				"ea2b2676c28c0db26d39331a336c6b92",
				"64719db2fb744db5b11e76a5288323cf",
				"148de99d1e9f33f8ba3f8e0593730413",
				"ca209002fada69add4520c1532bd0ee3",
				"78c6d9c637aeaf5d3fd0be1220ed841e",
				"0aeeeb12859935e447391ce0750788de" };

		for (int i = 0; i < names.length; i++) {
			int rank = 1;
			if (names[i].equals("Bodia")) rank = 3;
			else if (names[i].equals("pchesso")) rank = 2;
			
			pm.makePersistent(new RWMember(names[i], passwords[i], rank,
					countries[i]));
		}
		try {
			res.getOutputStream().println("Done!");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
