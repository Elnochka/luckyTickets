package console;

import core.LoadData;
import core.LoadDataImpl;
import core.StateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLuckyTickets {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLuckyTickets.class);

    public static void main(String[] args) {

        LoadData loadData = new LoadDataImpl();
        StateData stateData = new StateData(8);

        int count = loadData.findLuckyTicket(stateData);
        LOGGER.info("" + count);

    }
}
