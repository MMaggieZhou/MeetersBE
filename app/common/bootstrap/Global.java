
package common.bootstrap;


import common.cache.LRUAuthToken;

import play.Application;
import play.GlobalSettings;
import play.Logger;


public class Global extends GlobalSettings
{
    @Override
    public void onStart(Application app)
    {
    	LRUAuthToken.getInstance(200);
        Logger.info("----------------------Application has started--------------------");
    }

    @Override
    public void onStop(Application app)
    {
        Logger.info("------------------------@Application shutdown...");
    }
}
