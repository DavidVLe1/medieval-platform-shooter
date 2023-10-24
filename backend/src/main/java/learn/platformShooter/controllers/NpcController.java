package learn.platformShooter.controllers;

import learn.platformShooter.domain.NpcService;
import learn.platformShooter.models.Npc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/npc")
public class NpcController {
    private final NpcService npcService;

    public NpcController(NpcService npcService){this.npcService=npcService;}
    @GetMapping
    public List<Npc> findAll(){
        return npcService.findAll ();
    }

    @GetMapping("/{npcId}")
    public ResponseEntity<Npc> findById(@PathVariable int npcId){
        Npc npc = npcService.findById (npcId);
        if(npc==null){
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok (npc);
    }

    //implement create
    //implement update
    //implement delete
}
