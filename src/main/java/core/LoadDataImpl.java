package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class LoadDataImpl implements LoadData {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataImpl.class);

    @Override
    public int findLuckyTicket(StateData stateData) {

        DataForJson dataForJson = new DataForJson();

        StringBuilder curNum = getCurNum(stateData.getNumbers());
        stateData.setCurNum(curNum);

        getTickets(stateData, dataForJson);

        return stateData.getCountAll();
    }

    private boolean checkSum(char[] ticketArr){

        int sumLeft = Integer.parseInt(valueOf(ticketArr[0]))
                + Integer.parseInt(valueOf(ticketArr[1]))
                + Integer.parseInt(valueOf(ticketArr[2]));
        int sumRight = Integer.parseInt(valueOf(ticketArr[3]))
                + Integer.parseInt(valueOf(ticketArr[4]))
                + Integer.parseInt(valueOf(ticketArr[5]));

        return sumLeft == sumRight;

    }

    private boolean checkSum8(char[] ticketArr){

        int sumLeft = Integer.parseInt(valueOf(ticketArr[0]))
                + Integer.parseInt(valueOf(ticketArr[1]))
                + Integer.parseInt(valueOf(ticketArr[2]))
                + Integer.parseInt(valueOf(ticketArr[3]));
        int sumRight = Integer.parseInt(valueOf(ticketArr[4]))
                + Integer.parseInt(valueOf(ticketArr[5]))
                + Integer.parseInt(valueOf(ticketArr[6]))
                + Integer.parseInt(valueOf(ticketArr[7]));

        return sumLeft == sumRight;

    }

    private boolean checkSum10(char[] ticketArr){

        int sumLeft = Integer.parseInt(Character.toString(ticketArr[0]))
                + Integer.parseInt(Character.toString(ticketArr[1]))
                + Integer.parseInt(Character.toString(ticketArr[2]))
                + Integer.parseInt(Character.toString(ticketArr[3]))
                + Integer.parseInt(Character.toString(ticketArr[4]));
        int sumRight = Integer.parseInt(Character.toString(ticketArr[5]))
                + Integer.parseInt(Character.toString(ticketArr[6]))
                + Integer.parseInt(Character.toString(ticketArr[7]))
                + Integer.parseInt(Character.toString(ticketArr[8]))
                + Integer.parseInt(Character.toString(ticketArr[9]));

        return sumLeft == sumRight;

    }

    private boolean checkSum4(char[] ticketArr){

        int sumLeft = Integer.parseInt(valueOf(ticketArr[0])) + Integer.parseInt(valueOf(ticketArr[1]));
        int sumRight = Integer.parseInt(valueOf(ticketArr[2])) + Integer.parseInt(valueOf(ticketArr[3]));

        return sumLeft == sumRight;

    }

    private StringBuilder getCurNum(int num){
        String curNum;
        switch (num){
            case (4):
                curNum = "00";
                break;
            case (6):
                curNum = "000";
                break;
            case (8):
                curNum = "0000";
                break;
            case (10):
                curNum = "00000";
                break;
            default:
                curNum = "000";
                break;
        }
        return new StringBuilder(curNum);
    }

    private StringBuilder getLeftNum(char[] ticketArr, int num, StringBuilder leftNum){

        leftNum.setLength(0);
        switch (num){
            case (4):

                leftNum.append(Integer.parseInt(valueOf(ticketArr[0])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[1])));
                break;
            case (6):

                leftNum.append(Integer.parseInt(valueOf(ticketArr[0])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[1])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[2])));
                break;
            case (8):

                leftNum.append(Integer.parseInt(valueOf(ticketArr[0])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[1])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[2])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[3])));
                break;
            case (10):

                leftNum.append(Integer.parseInt(Character.toString(ticketArr[0])));
                leftNum.append(Integer.parseInt(Character.toString(ticketArr[1])));
                leftNum.append(Integer.parseInt(Character.toString(ticketArr[2])));
                leftNum.append(Integer.parseInt(Character.toString(ticketArr[3])));
                leftNum.append(Integer.parseInt(Character.toString(ticketArr[4])));
                break;
            default:

                leftNum.append(Integer.parseInt(valueOf(ticketArr[0])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[1])));
                leftNum.append(Integer.parseInt(valueOf(ticketArr[2])));
                break;
        }
        return leftNum;
    }

    private boolean checkSums(char[] ticketArr, int num){
        boolean checked = false;
        switch (num){
            case (4):
                if(checkSum4(ticketArr)){
                    checked = true;
                }
                break;
            case (6):
                if(checkSum(ticketArr)){
                    checked = true;
                }
                break;
            case (8):
                if(checkSum8(ticketArr)){
                    checked = true;
                }
                break;
            case (10):
                if(checkSum10(ticketArr)){
                    checked = true;
                }
                break;
            default:
                if(checkSum(ticketArr)){
                    checked = true;
                }
                break;
        }
        return checked;
    }

    private void saveForJson(DataForJson dataForJson, StringBuilder curNum, List<String> luckyList, int countOne){
        String curNumString = curNum.toString();
        dataForJson.getDataJson().put(curNumString, countOne);
        dataForJson.getListLucky().put(curNumString, luckyList);
    }

    private void getTickets(StateData stateData, DataForJson dataForJson){
        long engage;
        switch (stateData.getNumbers()) {
            case (4):
                engage = 9999L;
                break;
            case (6):
                engage = 999999L;
                break;
            case (8):
                engage = 99999999L;
                break;
            case (10):
                engage = 9999999999L;
                break;
            default:
                engage = 999999L;
                break;
        }

        char[] ticketArr;
        StringBuilder numLeft;
        StringBuilder ticket;
        for (long i = 1; i <= engage; i++) {

            ticket = getTicket(i, stateData.getNumbers(), stateData.getTicket());

            ticketArr = ticket.toString().toCharArray();

            numLeft = getLeftNum(ticketArr, stateData.getNumbers(), stateData.getHelperSb());

            if (compareSb(numLeft, stateData.getCurNum())) {
                saveState(numLeft, stateData, dataForJson);
                stateData.setCurNum(numLeft);
                stateData.setCountOne(0);
                stateData.setLuckyList(new ArrayList<>());
            }

            if(checkSums(ticketArr, stateData.getNumbers())){
                stateData.setCountOne(stateData.getCountOne() + 1);
                stateData.setCountAll(stateData.getCountAll() + 1);
                if (stateData.getNumbers() < 9) {
                    stateData.getLuckyList().add(ticket.toString());
                }
            }

            if (i == engage) {
                saveForJson(dataForJson, stateData.getCurNum(), stateData.getLuckyList(), stateData.getCountOne());
            }
        }

        writeJson(dataForJson, "outfile.json");

    }

    private StringBuilder getTicket(long i, int count, StringBuilder ticket){
        ticket.setLength(0);
        if(count == 4) {
            if (i < 10L) {
                ticket.append("000");
                ticket.append(i);
            } else if (i < 100L) {
                ticket.append("00");
                ticket.append(i);
            } else if (i < 1000L) {
                ticket.append("0");
                ticket.append(i);
            } else {
                ticket.append(i);
            }
        }
        if(count == 6) {
            if (i < 10L) {
                ticket.append("00000");
                ticket.append(i);
            } else if (i < 100L) {
                ticket.append("0000");
                ticket.append(i);
            } else if (i < 1000L) {
                ticket.append("000");
                ticket.append(i);
            } else if (i < 10000L) {
                ticket.append("00");
                ticket.append(i);
            } else if (i < 100000L) {
                ticket.append("0");
                ticket.append(i);
            } else {
                ticket.append(i);
            }
        }
        if(count == 8) {
            if (i < 10) {
                ticket.append("0000000");
                ticket.append(i);
            } else if (i < 100) {
                ticket.append("000000");
                ticket.append(i);
            } else if (i < 1000) {
                ticket.append("00000");
                ticket.append(i);
            } else if (i < 10000) {
                ticket.append("0000");
                ticket.append(i);
            } else if (i < 100000) {
                ticket.append("000");
                ticket.append(i);
            } else if (i < 1000000) {
                ticket.append("00");
                ticket.append(i);
            } else if (i < 10000000) {
                ticket.append("0");
                ticket.append(i);
            } else {
                ticket.append(i);
            }
        }
        if(count == 10) {
            if (i < 10L) {
                ticket.append("000000000");
                ticket.append(i);
            } else if (i < 100L) {
                ticket.append("00000000");
                ticket.append(i);
            } else if (i < 1000L) {
                ticket.append("0000000");
                ticket.append(i);
            } else if (i < 10000L) {
                ticket.append("000000");
                ticket.append(i);
            } else if (i < 100000L) {
                ticket.append("00000");
                ticket.append(i);
            } else if (i < 1000000L) {
                ticket.append("0000");
                ticket.append(i);
            } else if (i < 10000000L) {
                ticket.append("000");
                ticket.append(i);
            } else if (i < 100000000L) {
                ticket.append("00");
                ticket.append(i);
            } else if (i < 1000000000L) {
                ticket.append("0");
                ticket.append(i);
            } else {
                ticket.append(i);
            }
        }
        return ticket;
    }

    private boolean compareSb(StringBuilder sb, StringBuilder str){
        if(sb == str){
            return false;
        }

        if(sb == null || str == null){
            return true;
        }

        boolean isEqual = true;
        if(sb.length() == str.length()){
            for(int i = 0; i < sb.length(); i++){
                if(sb.charAt(i) != str.charAt(i)){
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }

        return !isEqual;
    }

    private void saveState(StringBuilder numLeft, StateData stateData, DataForJson dataForJson){
        saveForJson(dataForJson, stateData.getCurNum(), stateData.getLuckyList(), stateData.getCountOne());
        stateData.setCurNum(numLeft);
        stateData.setCountOne(0);
        stateData.setLuckyList(new ArrayList<>());
    }

    @Override
    public void writeJson(DataForJson dataForJson, String fileJson) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File(fileJson), dataForJson);

        }  catch (IOException e) {

            e.printStackTrace();
        }

        LOGGER.info("File outfile.json was created.");
    }
}
