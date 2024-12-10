package com.example.helper.service;

import com.example.helper.entity.medicine;
import com.example.helper.entity.medicinee;
import com.example.helper.entity.medicinetype;
import com.example.helper.repository.medicineRepository;
import com.example.helper.repository.medicineeRepository;
import com.example.helper.repository.medicinetypeRepository;
import com.example.helper.repository.pharmstorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class medicineservice {

    @Autowired
    private WebClient webClient;

    @Autowired
    private medicineRepository medicineRepository;

    @Autowired
    private medicineeRepository medicineeRepository;

    @Autowired
    private medicinetypeRepository medicinetypeRepository;

    @Autowired
    private pharmstorageRepository pharmstorageRepository;

    @Value("${api.service-key}")
    private String key;

    private final ResourceLoader resourceLoader;

    private final static String[] efcyQesitm = {"감기","타박상","근육통","발열","두통","복통","치통"};

    public medicineservice(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    //0.감기 1.두통 2.복통 3.피부상처및화상 4.소화불량 5.근육통 6.해열 7.치통,구내염

//    public void newmedicine() {
//
//        int[] a = {98,1114,77,970,1556,1820,1548,1803,159,1114,2774,159,198,776,2208};
//
//        for(int i = 0; i < a.length; i++) {
//            medicine bef = medicineRepository.findById(a[i]).get();
//            medicinee aft = new medicinee();
//            medicinetype type = new medicinetype();
//
//            aft.setId(bef.getId());
//            aft.setItemName(bef.getItemName());
//            aft.setEntpName(bef.getEntpName());
//            aft.setEfcyQesitm(bef.getEfcyQesitm());
//            aft.setUseMethod(bef.getUseMethod());
//            aft.setAtpnQesitm(bef.getAtpnQesitm());
//            aft.setIntrcQesitm(bef.getIntrcQesitm());
//            aft.setSeQesitm(bef.getSeQesitm());
//            aft.setDepositMethod(bef.getDepositMethod());
//            //type.setMedicine_id(bef.getId());
//            type.setType(7);
//            medicineeRepository.save(aft);
//            medicinetypeRepository.save(type);
//        }
//    }

//    Random random = new Random();
//    public void insertStorageAll(Integer id, Integer remain, Integer dur) {
//
//        for(int i = 1; i < 9; i++) {
//            pharmstorage ent = new pharmstorage();
//            ent.setPharm_id(i);
//            ent.setMedicine_id(id);
//            ent.setRemain(remain - random.nextInt(dur));
//            pharmstorageRepository.save(ent);
//        }
//    }
//
//    public void insertStorageRandom(Integer id, Integer remain, Integer dur,Integer num) {
//
//        List<Integer> numbers = new ArrayList<>();
//        for (int i = 1; i <= 8; i++) {
//            numbers.add(i);
//        }
//
//        // 리스트를 섞음
//        Collections.shuffle(numbers);
//
//        // 앞에서부터 4개의 숫자 선택
//        List<Integer> selectedNumbers = numbers.subList(0, num);
//
//        for(int i = 1; i < num; i++) {
//
//            pharmstorage ent = new pharmstorage();
//            random.nextInt();
//            ent.setPharm_id(selectedNumbers.get(i));
//            ent.setMedicine_id(id);
//            ent.setRemain(remain - random.nextInt(dur));
//            pharmstorageRepository.save(ent);
//        }
//    }



    public List<medicinee> getMedicineList(Integer efcy) throws IOException {

        List<medicinetype> medicinetypes = medicinetypeRepository.findByType(efcy);
        List<Integer> medicineIds = medicinetypes.stream()
                .map(medicinetype::getMedicine)
                .map(medicine::getId)
                .collect(Collectors.toList()).reversed();

        Pageable limitTen = PageRequest.of(0, 10); // 첫 페이지, 10개 제한
        List<medicinee> medicineList = medicinetypeRepository.findMedicinesByIds(medicineIds, limitTen);

        for (int i = 0; i < medicineList.size(); i++) {
            String filePath = "classpath:static/images/medicine/"+ medicineList.get(i).getItemName() + ".jpg";
            //String filePath = "classpath:static/images/medicine/고프레티엘에프캡슐.jpg";

            Resource resource = resourceLoader.getResource(filePath);
            try (InputStream inputStream = resource.getInputStream()) {
                byte[] fileBytes = inputStream.readAllBytes(); // InputStream으로 읽기
                medicineList.get(i).setImage(fileBytes);
            }
//            Path path = resource.getFile().toPath();
//
//            // 파일을 Base64로 인코딩
//            byte[] fileBytes = Files.readAllBytes(path);
//
//            medicineList.get(i).setImage(fileBytes);
        }
        return medicineList;
    }

    public medicinee getMedicine(Integer itemSeq) throws IOException {


        medicinee medicine = medicineeRepository.findById(itemSeq).get();
        String filePath = "classpath:static/images/medicine/"+ medicine.getItemName() + ".jpg";
        //String filePath = "classpath:static/images/medicine/고프레티엘에프캡슐.jpg";

        Resource resource = resourceLoader.getResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] fileBytes = inputStream.readAllBytes(); // InputStream으로 읽기
            medicine.setImage(fileBytes);
        }

        return medicine;
//        var response =
//                webClient
//                        .get()
//                        .uri(uriBuilder ->
//                                uriBuilder
//                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
//                                        .queryParam("serviceKey", key)
//                                        .queryParam("itemSeq", itemSeq)
//                                        .queryParam("pageNo", "1")
//                                        .queryParam("numOfRows", "10")
//                                        .queryParam("type", "json")
//                                        .build())
//                        .retrieve()
//                        .bodyToMono(medicineAPI.class)
//                        .block();
//
//        assert response != null;
//        return response;
    }

    public List<medicinee> getQueryMedicine(String query, Integer page) throws IOException {
        Pageable limitTen = PageRequest.of(0, 10); // 첫 페이지, 10개 제한
        String decodedValue = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
        List<medicinee> medicineList = medicineeRepository.findByItemNameContainingKeyword(decodedValue, limitTen);

        for (int i = 0; i < medicineList.size(); i++) {
            String filePath = "classpath:static/images/medicine/"+ medicineList.get(i).getItemName() + ".jpg";
            //String filePath = "classpath:static/images/medicine/고프레티엘에프캡슐.jpg";

            Resource resource = resourceLoader.getResource(filePath);
            try (InputStream inputStream = resource.getInputStream()) {
                byte[] fileBytes = inputStream.readAllBytes(); // InputStream으로 읽기
                medicineList.get(i).setImage(fileBytes);
            }
//            Path path = resource.getFile().toPath();
//
//            // 파일을 Base64로 인코딩
//            byte[] fileBytes = Files.readAllBytes(path);
//
//            medicineList.get(i).setImage(fileBytes);
        }
        return medicineList;
    }
//        var response =
//                webClient
//                        .get()
//                        .uri(uriBuilder ->
//                                uriBuilder
//                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
//                                        .queryParam("serviceKey", key)
//                                        .queryParam("itemName", query)
//                                        .queryParam("pageNo", "1")
//                                        .queryParam("numOfRows", "10")
//                                        .queryParam("type", "json")
//                                        .build())
//                        .retrieve()
//                        .bodyToMono(medicineAPI.class)
//                        .block();
//
//        assert response != null;
//        return response;
//    }

//    public void postmedicine(Integer a) {
//        var response =
//                webClient
//                        .get()
//                        .uri(uriBuilder ->
//                                uriBuilder
//                                        .path("/DrbEasyDrugInfoService/getDrbEasyDrugList")
//                                        .queryParam("serviceKey", key)
//                                        .queryParam("pageNo", a.toString())
//                                        .queryParam("numOfRows", "50")
//                                        .queryParam("type", "json")
//                                        .build())
//                        .retrieve()
//                        .bodyToMono(medicineAPI.class)
//                        .block();
//
//        for(int i = 0; i < 50; i++) {
//            medicine med = new medicine();
//
//            assert response != null;
//            medicineAPI.Body.Item item = response.getBody().getItems().get(i);
//            med.setItemName(item.getItemName());
//            med.setEntpName(item.getEntpName());
//            med.setEfcyQesitm(response.getBody().getItems().get(i).getEfcyQesitm());
//            med.setUseMethod(response.getBody().getItems().get(i).getUseMethodQesitm());
//            med.setAtpnQesitm(response.getBody().getItems().get(i).getAtpnQesitm());
//            med.setIntrcQesitm(response.getBody().getItems().get(i).getIntrcQesitm());
//            med.setSeQesitm(response.getBody().getItems().get(i).getSeQesitm());
//            med.setDepositMethod(response.getBody().getItems().get(i).getDepositMethodQesitm());
//            med.setItemImage(response.getBody().getItems().get(i).getItemImage());
//
//            medicineRepository.save(med);
//        }
//    }
}
