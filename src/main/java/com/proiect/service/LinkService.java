package com.proiect.service;

import com.proiect.dto.LinkDto;
import com.proiect.model.Link;

public interface LinkService {
    public LinkDto findLinkById(Long id);
    public LinkDto getAllLinks();
    public LinkDto createLink(LinkDto link);
    public LinkDto updateLink(LinkDto link);
    public void deleteLink(Long id);
}
