package cz.muni.ics;

import cz.muni.ics.DAOs.VoDAO;
import org.dataloader.DataLoaderRegistry;

public class AppWiring {

	//TODO: write data fetchers

	public static class Context {

		final DataLoaderRegistry dataLoaderRegistry;
		final VoDAO voDAO;

		public Context(VoDAO voDAO) {
			this.voDAO = voDAO;
			this.dataLoaderRegistry = new DataLoaderRegistry();
		}

		public DataLoaderRegistry getDataLoaderRegistry() {
			return dataLoaderRegistry;
		}

	}

}
