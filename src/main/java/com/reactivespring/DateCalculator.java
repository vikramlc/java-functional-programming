package com.reactivespring;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DateCalculator {

    private boolean isWithinRange(int year, LocalDate currentDate) {
        LocalDate sourceDate = LocalDate.of(year, 4, 1);
        LocalDate destinationDate = LocalDate.of(year, 6, 30);
        return !(currentDate.isBefore(sourceDate) || currentDate.isAfter(destinationDate));
    }

    public void calculate() {
        int cur = 2021;
        int mid = 2020;
        int prev = 2019;

        System.out.println("####################");

        System.out.println("ITRJsonMaps: ");

        String itrJsonFinal = "{\"_id\":\"022296d3-8056-47fa-b18d-6a9b8ef6120f\",\"pan\":\"AAICM2744A\",\"jsonData\":[{\"Status\":\"PVT COMPANY\",\"Area/Locality\":\"V ASHI\",\"TAX Details\":{\"Others\":null,\"TDS\":\"25106\",\"TCS\":\"2213\",\"Total Taxes Paid (7a+7b+7c+7d)\":\"306619\",\"Refund (7e-6)\":\"20\",\"Tax Payable (6-7e)\":null,\"Deductions under Chapter-VI-A\":\"0\",\"Total Tax and Interest Payable\":\"306603\",\"Gross Total Income\":\"970379\",\"Self Assessment Tax\":\"29300\",\"Exempt Income\":null,\"Interest Payable\":\"6756\",\"Advance Tax\":\"250000\",\"Total Income\":\"970380\",\"Agriculture\":null,\"Current Year loss, if any\":null,\"Taxes Paid\":null,\"Net Tax Payable\":\"299847\"},\"Town/City/District\":\"NAVI MUMBAI\",\"Name Of Premises/Building/Village\":\"SATRA PLAZA, PLOT NO. 19/20\",\"Acknowledgement Number\":null,\"Flat/Door/Block No\":\"SHOP NO. 43\",\"index\":{\"$numberInt\":\"0\"},\"Road/Street/Post Office\":\"SECTOR- 191), PALM BEACH ROAD\",\"Form No\":\"ITR-6\",\"Name\":\"M POWER INDIA PVT LTD\",\"Date Of Filing\":\"29-10-2017\",\"Assessment Year\":\"2017-18\",\"Pin\":\"400705\",\"Aadhaar Number\":null,\"State\":\"MAHARASHTRA\",\"PAN\":\"AAICM2744A\",\"Form Type\":\"ORIGINAL\",\"key\":\"others/6660320812693548_ITR_AY_17-18.pdf\"},{\"Status\":\"PVT COMPANY\",\"Area/Locality\":\"V ASHI\",\"TAX Details\":{\"Others\":null,\"TDS\":\"21902\",\"TCS\":\"2982\",\"Total Taxes Paid (7a+7b+7c+7d)\":\"349884\",\"Refund (7e-6)\":\"83460\",\"Tax Payable (6-7e)\":null,\"Deductions under Chapter-VI-A\":\"2\",\"Total Tax and Interest Payable\":\"266428\",\"Gross Total Income\":\"1026682\",\"Self Assessment Tax\":null,\"Exempt Income\":null,\"Interest Payable\":\"2058\",\"Advance Tax\":\"325000\",\"Total Income\":\"1026680\",\"Agriculture\":null,\"Current Year loss, if any\":null,\"Taxes Paid\":null,\"Net Tax Payable\":\"264370\"},\"Town/City/District\":\"NAVI MUMBAI\",\"Name Of Premises/Building/Village\":\"SATRA PLAZA, PLOT NO. 19/20\",\"Acknowledgement Number\":\"338719701181018\",\"Flat/Door/Block No\":\"SHOP NO. 43\",\"index\":{\"$numberInt\":\"1\"},\"Road/Street/Post Office\":\"SECTOR - 19D, PALM BEACH ROAD\",\"Form No\":\"ITR-6\",\"Name\":\"M POWER INDIA PVT LTD\",\"Date Of Filing\":\"18-10-2018\",\"Assessment Year\":\"2018-19\",\"Pin\":\"400705\",\"Aadhaar Number\":null,\"State\":\"MAHARASHTRA\",\"PAN\":\"AAICM2744A\",\"Form Type\":\"REVISED\",\"key\":\"others/6660326806609344_ITR_AY_18-19.pdf\"}],\"createDate\":\"2020-03-26T14:35:27.942\",\"mode\":\"OCR\",\"fileLocations\":[\"solvezy-dev-ocr-applog\"],\"deletionCount\":{\"$numberInt\":\"0\"},\"_class\":\"com.scv.bil.model.ITRTransDetl\"}";

        JSONObject itrJsonObj = new JSONObject(itrJsonFinal);
        Map<String, Object> objectMap = itrJsonObj.toMap();
        List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>)objectMap.get("jsonData");
        List<String> collect = listOfMaps.stream().filter(mapList -> mapList.containsKey("key"))
                .map(map -> {
                    String itrKey = map.get("key").toString();
                    int index = itrKey.indexOf("_");
                    return itrKey.substring(index+1);
                }).collect(Collectors.toList());

        collect.stream().forEach(list -> System.out.println("File Name: " + list));

        System.out.println("####################");

        LocalDate currentDate = LocalDate.of(2019, 4, 1);

        if(isWithinRange(2019, currentDate)) {
            System.out.println("This is within the FY range.");
        }

        System.out.println("####################");

        JSONObject itrJson = new JSONObject();
        Integer[] yearArray = {cur, mid, prev};
        List<Integer> financialYears = new ArrayList<Integer>(Arrays.asList(yearArray));
        AtomicInteger count = new AtomicInteger();
        financialYears.forEach(year -> {
            if(isWithin(year)) {
                itrJson.put("num_of_itrs", count.incrementAndGet());
            }
        });

        System.out.println("Unique non-repeating integers: " + findUniqueXor());

        System.out.println("#############################");

        String testJson = "{\n" +
                "  \"timeStamp\": \"19-03-2020 16:41:10.450\",\n" +
                "  \"bilId\": true,\n" +
                "  \"statusCode\": 400,\n" +
                "  \"errorMessage\": \"Data not availble for given bilID\"\n" +
                "}\n";

        JSONObject testJsonConvert = new JSONObject(testJson);
        if(testJsonConvert.toMap().get("bilId").equals(true)) {
            System.out.println("testJsonConvert: This is TRUE");
        }

        System.out.println("#############################");

//        System.out.println("Bil Stage Name: " + BilStageName.ITR.toString());

        System.out.println("#############################");

        Set<String> set = itrJson.toMap().keySet();
        if(!set.contains("num_of_itrs")) {
            System.out.println("Does not contain - num_of_itrs");
        }

        System.out.println(itrJson.get("num_of_itrs") + " number of itrs.");

        Object num_of_itrs = itrJson.get("num_of_itrs");

        if(String.valueOf(itrJson.get("num_of_itrs")).equals("1")) {
            System.out.println("Just ONE itr.");
        }

        if(itrJson.isEmpty()) {
            System.out.println("\"num_of_itrs\" is empty.");
        }

        System.out.println("Number of ITRs json: " + itrJson.toMap());
    }

    private boolean isWithin(int year) {
        LocalDate juneDate = LocalDate.of(year, 6, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-M-yyyy");
        LocalDate dateOfIncorp = LocalDate.parse("31-08-2019", formatter);
        LocalDate currentDate = LocalDate.parse("01-06-2021", formatter);
        return !(juneDate.isBefore(dateOfIncorp) || juneDate.isAfter(currentDate) || juneDate.isEqual(currentDate));
    }

    private int findUnique() {
        int[] nums = {2, 1, 2, 1, 5, 4, 5};

        Map<Integer, Integer> numMap = new HashMap<>();
        if((nums.length % 2) == 1) {
            for(int i=0; i<nums.length; i++) {
                if(numMap.containsKey(nums[i])) {
                    numMap.put(nums[i], 2);
                } else {
                    numMap.put(nums[i], 1);
                }
            }
        }

        if(numMap.containsValue(1)) {
            for(int key: numMap.keySet()) {
                if(numMap.get(key) == 1) {
                    return key;
                }
            }
        }
        return -1;
    }

    private int findUniqueXor() {
        int[] nums = {2, 1, 2, 1, 5, 4, 5};
        int uniqueNum = nums[0];
        for(int i=1; i<nums.length; i++) {
            uniqueNum = uniqueNum ^ nums[i];
        }

        return uniqueNum;
    }

}
