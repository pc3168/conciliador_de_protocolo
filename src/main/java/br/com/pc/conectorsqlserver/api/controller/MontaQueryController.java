package br.com.pc.conectorsqlserver.api.controller;


import br.com.pc.conectorsqlserver.config.ChooseDB;
import br.com.pc.conectorsqlserver.model.ModelBody;
import br.com.pc.conectorsqlserver.model.QueryResult;
import br.com.pc.conectorsqlserver.service.MontaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/query")
public class MontaQueryController {

    @Autowired
    private MontaQueryService montaQueryService;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<QueryResult> listDeMaps(
            @RequestParam("choose") ChooseDB chooseDB ,
            @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.ok(montaQueryService.listDeMaps(chooseDB, file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/body")
    @ResponseBody
    public ResponseEntity<QueryResult> listDeMaps(
            @RequestBody ModelBody modelBody) {
        return ResponseEntity.ok(montaQueryService.listDeMaps(modelBody.getChoose(), modelBody.getQuery()));
    }

    @GetMapping("/url")
    @ResponseBody
    public ResponseEntity<QueryResult> listDeMaps(
            @RequestParam("choose") ChooseDB chooseDB ,
            @RequestParam("query") String query) {
        return ResponseEntity.ok(montaQueryService.listDeMaps(chooseDB, query));
    }
}
