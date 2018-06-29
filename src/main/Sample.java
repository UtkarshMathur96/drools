package main;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.spi.KnowledgeHelper;

public class Sample {

    public static void main(String[] args) {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("rules/rules.drl"), ResourceType.DRL);
        builder.add(ResourceFactory.newClassPathResource("rules/mappings.dsl"), ResourceType.DSL);
        builder.add(ResourceFactory.newClassPathResource("rules/SimpleRules.dslr"), ResourceType.DSLR);

        if (builder.hasErrors()) {
            KnowledgeBuilderErrors e = builder.getErrors();
            System.out.println(e.toString());
            throw new IllegalArgumentException("Error parsing Knowledge Resources");
        }
        KnowledgeBase base = KnowledgeBaseFactory.newKnowledgeBase();
        base.addKnowledgePackages(builder.getKnowledgePackages());
        StatefulKnowledgeSession session = base.newStatefulKnowledgeSession();
        User user1 = new User("Utkarsh", "Superuser");
        User user2 = new User("Reader", "ReadOnly");
        session.insert(user1);
        session.insert(user2);
        session.fireAllRules();
        System.out.println("No of facts "+session.getFactCount());
        System.out.println();
        session.dispose();
    }
}
