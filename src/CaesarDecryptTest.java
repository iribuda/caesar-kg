import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CaesarDecryptTest {

    CaesarDecrypt caesarDecrypt;

    @BeforeEach
    void setUp() {
        String str = "Hello world blablabla";
        caesarDecrypt = new CaesarDecrypt(str);
    }

    @Test
    void findFrequencies() {
        HashMap<Character, Integer> actual = caesarDecrypt.findFrequencies();

        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 6);
        expected.put('o', 2);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('b', 3);
        expected.put('a', 3);

        assertEquals(expected, actual);
    }


    @Test
    void getFrequencyList() {
        ArrayList<Character> actual = caesarDecrypt.getFrequencyList();

        ArrayList<Character> expected = new ArrayList<>();
        expected.add('r');
        expected.add('w');
        expected.add('e');
        expected.add('h');
        expected.add('d');
        expected.add('o');
        expected.add('a');
        expected.add('b');
        expected.add('l');

        assertEquals(expected, actual);
    }

    @Test
    void findKey() {
        caesarDecrypt.setEncryptedText("Цпспщп вьмп обн");
        int actual = caesarDecrypt.findKey('о');
        int expected = 2;
        assertEquals(expected, actual);

        caesarDecrypt.setEncryptedText("Фнпнчн аъкн мял");
        int actual2 = caesarDecrypt.findKey('о');
        int expected2 = 34;
        assertEquals(expected2, actual2);
    }

    @Test
    void shift() {
        caesarDecrypt.setEncryptedText("шрурыр дюңр өго");
        String actual = caesarDecrypt.shift(3);

        String expected = "хорошо было нам";
        assertEquals(expected, actual);
    }

    @Test
    void decrypt(){
        caesarDecrypt.setEncryptedText("Япърсямъм, Цтаяй ёңк ёупуө\n" +
                "Яяжвямя ьлдр, лңк ёупуө,\n" +
                "Ярсъмгявъ Йойякя\n" +
                "Аярйям ёдпз аъпйъпяө,\n" +
                "Тцйям йтчсяи гъпйъпяө,\n" +
                "Йъжък йъкъц Жткөтйңп,\n" +
                "Йъмяи адквд зкзөсзп,\n" +
                "Йямгяиъм аяийяө йоплоййо,\n" +
                "Йяипяссттртм азклдййд\n" +
                "Йдн Атйяпвя йдкзөсзп.\n" +
                "\n" +
                "Йясъпя ёупуө ёңк цякъө,\n" +
                "Йямця аяясъп йңчцт якъө,\n" +
                "Ңпгңктт Атйяп чяяпъмя\n" +
                "Ясъмъм ңңжтм атпгт ьлз,\n" +
                "Яйаяксямъм ьп Цтаяй\n" +
                "Якърсям аяийяө стпгт ьлз.\n" +
                "\n" +
                "Аттптвтө йдквдм Цтаяйсъ\n" +
                "Атйяпгъй йопуө йдө яисяс,\n" +
                "Йдө яисйямгя мдмз яисяс;");
        String actual = caesarDecrypt.decrypt();

        String expected = "Арыстанын, Чубак жол жүрүп\n" +
                "Аазгана эмес, мол жүрүп,\n" +
                "Астындагы Көкала\n" +
                "Баскан жери быркырап,\n" +
                "Учкан куштай дыркырап,\n" +
                "Кызыл кылыч Зулпукор,\n" +
                "Кынай белге илиптир,\n" +
                "Кандайын байкап көрмөккө,\n" +
                "Кайраттуусун билмекке\n" +
                "Кең Букарга келиптир.\n" +
                "\n" +
                "Катыра жүрүп жол чалып,\n" +
                "Канча баатыр кошчу алып,\n" +
                "Ордолуу Букар шаарына\n" +
                "Атынын оозун бурду эми,\n" +
                "Акбалтанын эр Чубак\n" +
                "Алыстан байкап турду эми.\n" +
                "\n" +
                "Бууругуп келген Чубакты\n" +
                "Букардык көрүп кеп айтат,\n" +
                "Кеп айтканда нени айтат;";

        assertEquals(expected, actual);
    }
}