package learn.platformShooter.controllers;

import learn.platformShooter.domain.NpcService;
import learn.platformShooter.domain.Result;
import learn.platformShooter.models.Npc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/npc")
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
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Npc npc){
        Result<Npc> result = npcService.add (npc);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
    //implement update
    @PutMapping("/{npcId}")
    public ResponseEntity<Object> update(@PathVariable int npcId,@RequestBody Npc npc) {
        if (npcId != npc.getNpcId ()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Result<Npc> result = npcService.update (npc);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
    //implement delete
    @DeleteMapping("/{npcId}")
    public ResponseEntity<Npc> deleteById(@PathVariable int npcId) {
        Result<Npc> result =npcService.deleteById (npcId);
        if (result.isSuccess ()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
