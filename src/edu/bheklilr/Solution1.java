package edu.bheklilr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 implements Solution {

    private static final String INPUT = (
            "18144568296689784866596347266193986531397687719431268499352125948651752796139671" +
            "75618548254539631811343795749183732137321846977466683996316426223736844253261125" +
            "85283946462323363991753895647177797691214784149215198715986947573668987188746878" +
            "67839962453379255165133597984713197596567795775557135893466532748728731246777118" +
            "79814247855147854217817819764773267126743119947359479873835166998979165954332282" +
            "94198759715959469578766739518475118771755787196238772345762941477359483456641194" +
            "68533352832958111378859984362132631359235416784646641594356618319294621768993617" +
            "48844931993686815149586696152263625386228983677286629412756589171241673534963346" +
            "64239539753835439929664552886538885727235662548783529353611441231681613535447417" +
            "94191147939155848144393313428385287951139542948915243599666923268121562772372356" +
            "58722912968785283347733916266724918787622889535974992183971466856793874386348573" +
            "58552943964839321464529237533868734473777756775687759355878519113426969197211824" +
            "32589337681255679848332599412874324254489962521576585192395979819756283131389137" +
            "17359737613844646853162733435418527585253181446813644921734651745128566182927854" +
            "83181956548813344752352933634979165667651165776587656468598791994573513652324764" +
            "68751534595962149334662382196555475561521985584296993226941483944688761373817456" +
            "79895128577855663522859889919464361486528393915931787366249572149175277595742351" +
            "33666461988355855613377789115472297915429318142824465141688559333787512328799783" +
            "53928582647181827981845767441735433545439564443588938629769562537825661355891169" +
            "51453977795765263972417951812943227976871683266964972566849438296666723411626564" +
            "79563522892141714998477865114944671225898297338685958644728534192317628618817551" +
            "49297525136423397437472496848363751887658394682881999432112955651153761925338198" +
            "15443941121846555869646551641925523525346262959969687623888272948733627196366161" +
            "82786976922445125551927969267591395292198155775434997827738862786341543524544822" +
            "32111213181547582994562578756136995626482665146157594846278286997265434374961793" +
            "91323533993347442652861511779315945148575636643292997134369147211197469321594562" +
            "87267887878779218815883191236858656959258484139254446341"
    );

    @Override
    public void solve() {
        List<Integer> parsed = Arrays.stream(INPUT.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        parsed.add(parsed.get(0));

        int sum = 0;
        for (int i = 0; i < parsed.size() - 1; i++) {
            if (parsed.get(i) == parsed.get(i + 1)) {
                sum += parsed.get(i);
            }
        }
        System.out.println(sum);
    }
}