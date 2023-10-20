package learn.platformShooter.domain;

import learn.platformShooter.data.NpcRepository;
import learn.platformShooter.models.Npc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class NpcServiceTest {
    @Autowired
    NpcService service;
    @MockBean
    NpcRepository repository;

    @Test
    void shouldFindAll() {
        Npc npc1=new Npc (1,"Vendor","Health",10.0);
        Npc npc2=new Npc (2,"Shoe Vendor","Speed",5.0);
        List<Npc> npcList = List.of(npc1,npc2);
        when(repository.findAll ()).thenReturn (npcList);
        assertEquals (npcList,service.findAll ());
    }

    @Test
    void shouldFindById() {
        Npc expected = makeNpc ();
        when(repository.findById (1)).thenReturn (expected);
        Npc actual =service.findById (1);
        assertEquals (expected,actual);
        assertNull (service.findById (20));
    }

    @Test
    void shouldAdd() {
        Npc npc = makeNpc ();
        npc.setNpcId (0);
        Npc expected= makeNpc ();
        expected.setNpcId (3);
        when(repository.add (npc)).thenReturn (expected);

        Result<Npc> result = service.add (npc);
        assertEquals (ResultType.SUCCESS,result.getType ());
        assertEquals (expected,result.getPayload ());
    }
    @Test
    void shouldNotAdd() {
        //can't add null
        Result<Npc> result=service.add (null);
        assertEquals(ResultType.INVALID, result.getType());

        //npcId must be 0
        Npc npc = makeNpc ();
        npc.setNpcId (1);
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("npcId cannot be set for `add` operation.",result.getMessages ().get (0));

        //npc name is required
        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setNpcName ("");
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc name is required.",result.getMessages ().get (0));

        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setNpcName (null);
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc name is required.",result.getMessages ().get (0));

        //npc stat type is required
        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrementType ("");
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc stat type is required.",result.getMessages ().get (0));

        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrementType (null);
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc stat type is required.",result.getMessages ().get (0));
        //npc stat increment can;t be negative. only 0 and up.
        npc=makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrement (-100.0);
        result=service.add (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc statIncrement can't be negative.",result.getMessages ().get (0));
    }

    @Test
    void shouldUpdate() {
        Npc npc = makeNpc ();
        npc.setNpcId (2);
        npc.setNpcName ("Dealer");
        when(repository.update (npc)).thenReturn (true);
        Result<Npc> actual = service.update (npc);
        assertEquals (ResultType.SUCCESS,actual.getType ());
    }
    @Test
    void shouldNotUpdate() {
        //can't add null
        Result<Npc> result=service.update (null);
        assertEquals(ResultType.INVALID, result.getType());

        //npcId must NOT be 0 when updating
        Npc npc = makeNpc ();
        npc.setNpcId (0);
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("npcId must be set for `update` operation.",result.getMessages ().get (0));

        //should not update when npc not found
        npc=makeNpc ();
        npc.setNpcId (20);
        when(repository.update (npc)).thenReturn (false);
        result=service.update (npc);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("npcId: %s, not found", 20), result.getMessages().get(0));


        //npc name is required
        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setNpcName ("");
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc name is required.",result.getMessages ().get (0));

        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setNpcName (null);
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc name is required.",result.getMessages ().get (0));

        //npc stat type is required
        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrementType ("");
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc stat type is required.",result.getMessages ().get (0));

        npc = makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrementType (null);
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc stat type is required.",result.getMessages ().get (0));
        //npc stat increment can;t be negative. only 0 and up.
        npc=makeNpc ();
        npc.setNpcId (0);
        npc.setStatIncrement (-100.0);
        result=service.update (npc);
        assertEquals (ResultType.INVALID,result.getType ());
        assertEquals ("Npc statIncrement can't be negative.",result.getMessages ().get (0));
    }

    @Test
    void shouldDeleteById() {

        when(repository.deleteById (1)).thenReturn (true);
        Result<Npc> result=service.deleteById (1);
        assertEquals (ResultType.SUCCESS,result.getType ());
    }
    @Test
    void shouldNotDeleteById() {

        when(repository.deleteById (20)).thenReturn (false);
        Result<Npc> result=service.deleteById (20);
        assertEquals (ResultType.NOT_FOUND,result.getType ());
        assertEquals (String.format ("Npc id: %s, not found",20),result.getMessages ().get (0));
    }
    Npc makeNpc(){
        //npc_id, npc_name, stat_increment_type, stat_increment
        Npc npc = new Npc ();
        npc.setNpcId (1);
        npc.setNpcName ("Vendor");
        npc.setStatIncrementType ("Health");
        npc.setStatIncrement (10.0);
        return npc;
    }
}