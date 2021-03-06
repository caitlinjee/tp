package seedu.address.logic.parser.recipe;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.GetEditRecipeCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GetEditRecipeCommand object
 */
public class GetEditRecipeCommandParser implements Parser<GetEditRecipeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GetEditRecipeCommand
     * and returns a GetEditRecipeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GetEditRecipeCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert(index.getZeroBased() >= 0);
            return new GetEditRecipeCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GetEditRecipeCommand.MESSAGE_USAGE));
        }
    }

}
