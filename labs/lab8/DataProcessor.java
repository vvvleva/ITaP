import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // Доступ аннотации во время выполнения программы
@Target(ElementType.METHOD) // Указание на то, что аннотация может быть применена только к методам
public @interface DataProcessor {
    String comment() default ""; // Возможность добавления комментария к аннотации по умолчанию
}
