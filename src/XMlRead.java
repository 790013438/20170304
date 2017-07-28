import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by 79001 on 2017/7/28.
 */
public class XMlRead {
    /**
     * 题目四：解析XML文件信息（共8分）
     用任意方式解析出test.txt文件,输出图中信息
     Xml文件格式如下：请使用文件夹中的test4.xml文件
 
     输出格式如下：
     */
    public static void main (String[] args) {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File("C:\\Users\\79001\\Desktop\\上机题目\\test4.xml"));
            Element element = document.getRootElement().element("systemConfig");
            System.out.println("城市名称：" + element.elementText("CityName"));
            System.out.println("城市编号：" + element.elementText("CityCode"));
            System.out.println("地区编号：" + element.elementText("areaCode"));
            List<Element> eleList = element.element("ComeChannel").elements("Item");
            System.out.print("信息渠道：");
            StringBuilder stringBuilder = new StringBuilder();
            for (Element ele : eleList) {
                stringBuilder.append(ele.attribute("value").getValue()).append("、");
            }
            int n = stringBuilder.length();
            stringBuilder.delete(n - 1, n);
            System.out.println(stringBuilder);
            System.out.println("购车预算：");
            eleList = element.element("BuyCarBudget").elements("Item");
            for (int i = 0; i < eleList.size(); ++i) {
                System.out.println("\t选择" + (i + 1) + "：" + eleList.get(i).attribute("value").getValue());
            }
            System.out.println("意向颜色");
            eleList = element.element("IntentionColor").elements("Item");
            for (int i = 0; i < eleList.size(); ++i) {
                System.out.println("\t方案" + (i + 1) + "：" + eleList.get(i).attribute("value").getValue() + "色");
            }
        } catch (Exception e) {
            System.out.println("解析了不存在的属性 :)");
            e.printStackTrace();
        }
    }
}
