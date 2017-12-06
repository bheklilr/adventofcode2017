package edu.bheklilr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution01 implements Solution<Integer> {

    public static void main(String[] args) {
        long iters = 10000;
        Solution01 self = new Solution01();
        System.out.println(self.solvePart1());
        System.out.println(self.do_work_faster());
        long start = System.nanoTime();
        for (int i = 0; i < iters; i++) {
            self.do_work_faster();
        }
        long stop = System.nanoTime();
        double avg = ((double) (stop - start)) / iters;
        System.out.println("Took about " + avg + "ns per run");
    }

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
    private static final int[] INPUT_ARR = {
            1,8,1,4,4,5,6,8,2,9,6,6,8,9,7,8,4,8,6,6,5,9,6,3,4,7,2,6,6,1,9,3,9,8,6,5,3,1,3,9,
            7,6,8,7,7,1,9,4,3,1,2,6,8,4,9,9,3,5,2,1,2,5,9,4,8,6,5,1,7,5,2,7,9,6,1,3,9,6,7,1,
            7,5,6,1,8,5,4,8,2,5,4,5,3,9,6,3,1,8,1,1,3,4,3,7,9,5,7,4,9,1,8,3,7,3,2,1,3,7,3,2,
            1,8,4,6,9,7,7,4,6,6,6,8,3,9,9,6,3,1,6,4,2,6,2,2,3,7,3,6,8,4,4,2,5,3,2,6,1,1,2,5,
            8,5,2,8,3,9,4,6,4,6,2,3,2,3,3,6,3,9,9,1,7,5,3,8,9,5,6,4,7,1,7,7,7,9,7,6,9,1,2,1,
            4,7,8,4,1,4,9,2,1,5,1,9,8,7,1,5,9,8,6,9,4,7,5,7,3,6,6,8,9,8,7,1,8,8,7,4,6,8,7,8,
            6,7,8,3,9,9,6,2,4,5,3,3,7,9,2,5,5,1,6,5,1,3,3,5,9,7,9,8,4,7,1,3,1,9,7,5,9,6,5,6,
            7,7,9,5,7,7,5,5,5,7,1,3,5,8,9,3,4,6,6,5,3,2,7,4,8,7,2,8,7,3,1,2,4,6,7,7,7,1,1,8,
            7,9,8,1,4,2,4,7,8,5,5,1,4,7,8,5,4,2,1,7,8,1,7,8,1,9,7,6,4,7,7,3,2,6,7,1,2,6,7,4,
            3,1,1,9,9,4,7,3,5,9,4,7,9,8,7,3,8,3,5,1,6,6,9,9,8,9,7,9,1,6,5,9,5,4,3,3,2,2,8,2,
            9,4,1,9,8,7,5,9,7,1,5,9,5,9,4,6,9,5,7,8,7,6,6,7,3,9,5,1,8,4,7,5,1,1,8,7,7,1,7,5,
            5,7,8,7,1,9,6,2,3,8,7,7,2,3,4,5,7,6,2,9,4,1,4,7,7,3,5,9,4,8,3,4,5,6,6,4,1,1,9,4,
            6,8,5,3,3,3,5,2,8,3,2,9,5,8,1,1,1,3,7,8,8,5,9,9,8,4,3,6,2,1,3,2,6,3,1,3,5,9,2,3,
            5,4,1,6,7,8,4,6,4,6,6,4,1,5,9,4,3,5,6,6,1,8,3,1,9,2,9,4,6,2,1,7,6,8,9,9,3,6,1,7,
            4,8,8,4,4,9,3,1,9,9,3,6,8,6,8,1,5,1,4,9,5,8,6,6,9,6,1,5,2,2,6,3,6,2,5,3,8,6,2,2,
            8,9,8,3,6,7,7,2,8,6,6,2,9,4,1,2,7,5,6,5,8,9,1,7,1,2,4,1,6,7,3,5,3,4,9,6,3,3,4,6,
            6,4,2,3,9,5,3,9,7,5,3,8,3,5,4,3,9,9,2,9,6,6,4,5,5,2,8,8,6,5,3,8,8,8,5,7,2,7,2,3,
            5,6,6,2,5,4,8,7,8,3,5,2,9,3,5,3,6,1,1,4,4,1,2,3,1,6,8,1,6,1,3,5,3,5,4,4,7,4,1,7,
            9,4,1,9,1,1,4,7,9,3,9,1,5,5,8,4,8,1,4,4,3,9,3,3,1,3,4,2,8,3,8,5,2,8,7,9,5,1,1,3,
            9,5,4,2,9,4,8,9,1,5,2,4,3,5,9,9,6,6,6,9,2,3,2,6,8,1,2,1,5,6,2,7,7,2,3,7,2,3,5,6,
            5,8,7,2,2,9,1,2,9,6,8,7,8,5,2,8,3,3,4,7,7,3,3,9,1,6,2,6,6,7,2,4,9,1,8,7,8,7,6,2,
            2,8,8,9,5,3,5,9,7,4,9,9,2,1,8,3,9,7,1,4,6,6,8,5,6,7,9,3,8,7,4,3,8,6,3,4,8,5,7,3,
            5,8,5,5,2,9,4,3,9,6,4,8,3,9,3,2,1,4,6,4,5,2,9,2,3,7,5,3,3,8,6,8,7,3,4,4,7,3,7,7,
            7,7,5,6,7,7,5,6,8,7,7,5,9,3,5,5,8,7,8,5,1,9,1,1,3,4,2,6,9,6,9,1,9,7,2,1,1,8,2,4,
            3,2,5,8,9,3,3,7,6,8,1,2,5,5,6,7,9,8,4,8,3,3,2,5,9,9,4,1,2,8,7,4,3,2,4,2,5,4,4,8,
            9,9,6,2,5,2,1,5,7,6,5,8,5,1,9,2,3,9,5,9,7,9,8,1,9,7,5,6,2,8,3,1,3,1,3,8,9,1,3,7,
            1,7,3,5,9,7,3,7,6,1,3,8,4,4,6,4,6,8,5,3,1,6,2,7,3,3,4,3,5,4,1,8,5,2,7,5,8,5,2,5,
            3,1,8,1,4,4,6,8,1,3,6,4,4,9,2,1,7,3,4,6,5,1,7,4,5,1,2,8,5,6,6,1,8,2,9,2,7,8,5,4,
            8,3,1,8,1,9,5,6,5,4,8,8,1,3,3,4,4,7,5,2,3,5,2,9,3,3,6,3,4,9,7,9,1,6,5,6,6,7,6,5,
            1,1,6,5,7,7,6,5,8,7,6,5,6,4,6,8,5,9,8,7,9,1,9,9,4,5,7,3,5,1,3,6,5,2,3,2,4,7,6,4,
            6,8,7,5,1,5,3,4,5,9,5,9,6,2,1,4,9,3,3,4,6,6,2,3,8,2,1,9,6,5,5,5,4,7,5,5,6,1,5,2,
            1,9,8,5,5,8,4,2,9,6,9,9,3,2,2,6,9,4,1,4,8,3,9,4,4,6,8,8,7,6,1,3,7,3,8,1,7,4,5,6,
            7,9,8,9,5,1,2,8,5,7,7,8,5,5,6,6,3,5,2,2,8,5,9,8,8,9,9,1,9,4,6,4,3,6,1,4,8,6,5,2,
            8,3,9,3,9,1,5,9,3,1,7,8,7,3,6,6,2,4,9,5,7,2,1,4,9,1,7,5,2,7,7,5,9,5,7,4,2,3,5,1,
            3,3,6,6,6,4,6,1,9,8,8,3,5,5,8,5,5,6,1,3,3,7,7,7,8,9,1,1,5,4,7,2,2,9,7,9,1,5,4,2,
            9,3,1,8,1,4,2,8,2,4,4,6,5,1,4,1,6,8,8,5,5,9,3,3,3,7,8,7,5,1,2,3,2,8,7,9,9,7,8,3,
            5,3,9,2,8,5,8,2,6,4,7,1,8,1,8,2,7,9,8,1,8,4,5,7,6,7,4,4,1,7,3,5,4,3,3,5,4,5,4,3,
            9,5,6,4,4,4,3,5,8,8,9,3,8,6,2,9,7,6,9,5,6,2,5,3,7,8,2,5,6,6,1,3,5,5,8,9,1,1,6,9,
            5,1,4,5,3,9,7,7,7,9,5,7,6,5,2,6,3,9,7,2,4,1,7,9,5,1,8,1,2,9,4,3,2,2,7,9,7,6,8,7,
            1,6,8,3,2,6,6,9,6,4,9,7,2,5,6,6,8,4,9,4,3,8,2,9,6,6,6,6,7,2,3,4,1,1,6,2,6,5,6,4,
            7,9,5,6,3,5,2,2,8,9,2,1,4,1,7,1,4,9,9,8,4,7,7,8,6,5,1,1,4,9,4,4,6,7,1,2,2,5,8,9,
            8,2,9,7,3,3,8,6,8,5,9,5,8,6,4,4,7,2,8,5,3,4,1,9,2,3,1,7,6,2,8,6,1,8,8,1,7,5,5,1,
            4,9,2,9,7,5,2,5,1,3,6,4,2,3,3,9,7,4,3,7,4,7,2,4,9,6,8,4,8,3,6,3,7,5,1,8,8,7,6,5,
            8,3,9,4,6,8,2,8,8,1,9,9,9,4,3,2,1,1,2,9,5,5,6,5,1,1,5,3,7,6,1,9,2,5,3,3,8,1,9,8,
            1,5,4,4,3,9,4,1,1,2,1,8,4,6,5,5,5,8,6,9,6,4,6,5,5,1,6,4,1,9,2,5,5,2,3,5,2,5,3,4,
            6,2,6,2,9,5,9,9,6,9,6,8,7,6,2,3,8,8,8,2,7,2,9,4,8,7,3,3,6,2,7,1,9,6,3,6,6,1,6,1,
            8,2,7,8,6,9,7,6,9,2,2,4,4,5,1,2,5,5,5,1,9,2,7,9,6,9,2,6,7,5,9,1,3,9,5,2,9,2,1,9,
            8,1,5,5,7,7,5,4,3,4,9,9,7,8,2,7,7,3,8,8,6,2,7,8,6,3,4,1,5,4,3,5,2,4,5,4,4,8,2,2,
            3,2,1,1,1,2,1,3,1,8,1,5,4,7,5,8,2,9,9,4,5,6,2,5,7,8,7,5,6,1,3,6,9,9,5,6,2,6,4,8,
            2,6,6,5,1,4,6,1,5,7,5,9,4,8,4,6,2,7,8,2,8,6,9,9,7,2,6,5,4,3,4,3,7,4,9,6,1,7,9,3,
            9,1,3,2,3,5,3,3,9,9,3,3,4,7,4,4,2,6,5,2,8,6,1,5,1,1,7,7,9,3,1,5,9,4,5,1,4,8,5,7,
            5,6,3,6,6,4,3,2,9,2,9,9,7,1,3,4,3,6,9,1,4,7,2,1,1,1,9,7,4,6,9,3,2,1,5,9,4,5,6,2,
            8,7,2,6,7,8,8,7,8,7,8,7,7,9,2,1,8,8,1,5,8,8,3,1,9,1,2,3,6,8,5,8,6,5,6,9,5,9,2,5,
            8,4,8,4,1,3,9,2,5,4,4,4,6,3,4,1
    };

    private int do_work_faster() {
        int sum = 0;
        int prev = INPUT_ARR[INPUT_ARR.length - 1];
        for (int i = 0; i < INPUT_ARR.length; i++) {
            if (prev == INPUT_ARR[i]) {
                sum += prev;
            }
            prev = INPUT_ARR[i];
        }
        return sum;
    }

    @Override
    public Integer solvePart1() {
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
        return sum;
    }

    @Override
    public Integer solvePart2() {
        int sum = 0;
        final int size = INPUT_ARR.length;
        final int halfsize = size / 2;
        for (int i = 0; i < size; i++) {
            if (INPUT_ARR[i] == INPUT_ARR[(i + halfsize) % size]) {
                sum += INPUT_ARR[i];
            }
        }
        return sum;
    }
}
