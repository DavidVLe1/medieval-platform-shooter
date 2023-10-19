package learn.platformShooter.domain;

import learn.platformShooter.data.NpcRepository;
import learn.platformShooter.models.Npc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NpcService {
    private final NpcRepository repository;
    public NpcService(NpcRepository repository){this.repository=repository;}

    public List<Npc> findAll(){
        return repository.findAll ();
    }
    public Npc findById(int npcId){
        return repository.findById (npcId);
    }

    //don't need findByName for npc.

    public Result<Npc> add(Npc npc){
        Result<Npc> result = validate (npc);

        if (!result.isSuccess()) {
            return result;
        }

        if (npc.getNpcId () != 0) {
            result.addMessage("npcId cannot be set for `add` operation.", ResultType.INVALID);
            return result;
        }
        npc = repository.add(npc);
        result.setPayload(npc);
        return result;

    }
    public Result<Npc> update(Npc npc){
        Result<Npc> result = validate (npc);

        if (!result.isSuccess()) {
            return result;
        }

        if (npc.getNpcId () <= 0) {
            result.addMessage("npcId must be set for `update` operation.", ResultType.INVALID);
            return result;
        }
        if (!repository.update(npc)) {
            String msg = String.format("npcId: %s, not found", npc.getNpcId ());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }
    public Result<Npc> deleteById(int npcId){
        Result<Npc> result = new Result<> ();
        if (!repository.deleteById(npcId)) {
            String msg = String.format("Npc id: %s, not found", npcId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        }
        return result;
    }

    public Result<Npc> validate(Npc npc){
        Result<Npc> result = new Result<> ();
        if (npc == null) {
            result.addMessage("Npc cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(npc.getNpcName ())) {
            result.addMessage("Npc name is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(npc.getStatIncrementType ())) {
            result.addMessage("Npc stat type is required", ResultType.INVALID);
        }
        return result;
    }
}
